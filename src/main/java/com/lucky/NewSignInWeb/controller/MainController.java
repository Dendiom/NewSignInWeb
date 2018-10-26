package com.lucky.NewSignInWeb.controller;

import com.lucky.NewSignInWeb.bean.Record;
import com.lucky.NewSignInWeb.bean.Result;
import com.lucky.NewSignInWeb.bean.User;
import com.lucky.NewSignInWeb.constant.Constants;
import com.lucky.NewSignInWeb.enums.Code;
import com.lucky.NewSignInWeb.service.RankService;
import com.lucky.NewSignInWeb.service.RecordService;
import com.lucky.NewSignInWeb.util.TimeUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Controller()
@RequestMapping("/main")
public class MainController {

    @Autowired
    private RankService rankService;

    @Autowired
    private RecordService recordService;

    private static final String GET = "get";
    private static final String SIGN_IN = "sign_in";
    private static final String SIGN_OUT = "sign_out";

    @GetMapping("/count.do")
    public ModelAndView getCount(HttpServletRequest req,
                         @RequestParam(value = "method", required = false) String method) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.SessionAttrs.USER);
        ModelAndView modelAndView = new ModelAndView("main/count");

        if (method == null || GET.equals(method)) {
            getRecord(modelAndView, user, session);
        } else if (SIGN_IN.equals(method)) {
            signIn(modelAndView, user, session);
        } else if (SIGN_OUT.equals(method)) {
            signOut(modelAndView, user, session);
        }

        return modelAndView;
    }

    @GetMapping("/rank.do")
    public ModelAndView getRank() {
        Result result = rankService.getRank(TimeUtil.getWeekIdentifier());
        ModelAndView modelAndView = new ModelAndView("main/rank");
        if (result.getCode() == Code.SUCCESS) {
            modelAndView.addObject(Constants.ReqAttrs.RANK, result.getObj());
            return modelAndView;
        }

        modelAndView.addObject(Constants.ReqAttrs.ERROR, result);
        return modelAndView;
    }

    /**
     * 获取本周签到数据.
     */
    @SuppressWarnings(value = "unchecked")
    private void getRecord(ModelAndView model, User user, HttpSession session) {
        Result result = recordService.getWeekRecords(user.getUsername(), TimeUtil.getWeekIdentifier());
        if (result.getCode() == Code.SUCCESS) {
            List<Record> records = (List) result.getObj();
            //处理数据
            long totalTime = 0;
            Record[] weekRecords = new Record[7];
            for (Record record : records) {
                totalTime += record.getCount();
                weekRecords[record.getDay_of_week() - 1] = record;
            }

            for (int i = 0; i < 7; i++) {
                if (weekRecords[i] == null) {
                    weekRecords[i] = new Record();
                }
            }

            //TODO total time and save to session
            float hour = (float) totalTime / (60 * 60);
            DecimalFormat decimalFormat = new DecimalFormat(".0");
            String hourStr = decimalFormat.format(hour);
            session.setAttribute(Constants.SessionAttrs.RECORD, weekRecords);
            session.setAttribute(Constants.SessionAttrs.TOTAL_TIME, hourStr);
            return;
        }

        model.addObject(Constants.ReqAttrs.ERROR, result);
    }

    /**
     * 签到.
     */
    private void signIn(ModelAndView model, User user, HttpSession session) {
        Record[] records = (Record[]) session.getAttribute(Constants.SessionAttrs.RECORD);
        if (records == null) {
            // avoid session timeout
            getRecord(model, user, session);
            records = (Record[]) session.getAttribute(Constants.SessionAttrs.RECORD);
        }

        int periodOfDay = TimeUtil.getDayPeriod(); // 0, 1, 2
        int dayOfPeriod = TimeUtil.getDayOfWeek(); // 1~7
        Date now = new Date();
        Record record = records[dayOfPeriod - 1];

        if (record.getId() == null) { // no record exits
            Result result = recordService.signInFirstTime(user.getUsername(), now);
            if (result.getCode() == Code.SUCCESS) {
                getRecord(model, user, session);
                return;
            }

            model.addObject(Constants.ReqAttrs.ERROR, result);
            return;
        }

        // judge if have signed in
        switch (periodOfDay) {
            case 0:
                if (record.getIn_time_mor() != null) { // have signed in already
                    model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.HAVE_SIGN_IN, "您已经签到"));
                    return;
                }
                break;
            case 1:
                if (record.getIn_time_noon() != null) { // have signed in already
                    model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.HAVE_SIGN_IN, "您已经签到"));
                    return;
                }
                break;
            case 2:
                if (record.getIn_time_eve() != null) { // have signed in already
                    model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.HAVE_SIGN_IN, "您已经签到"));
                    return;
                }
                break;
            default:
                break;
        }

        // need to update record
        Result result = recordService.signInUpdate(record.getId().longValue(), now);
        if (result.getCode() == Code.SUCCESS) {
            getRecord(model, user, session);
            return;
        }

        model.addObject(Constants.ReqAttrs.ERROR, result);
    }

    /**
     * 签出.
     */
    private void signOut(ModelAndView model, User user, HttpSession session) {
        Record[] records = (Record[]) session.getAttribute(Constants.SessionAttrs.RECORD);
        if (records == null) {
            // avoid session timeout
            getRecord(model, user, session);
            records = (Record[]) session.getAttribute(Constants.SessionAttrs.RECORD);
        }

        int periodOfDay = TimeUtil.getDayPeriod(); // 0, 1, 2
        int dayOfPeriod = TimeUtil.getDayOfWeek(); // 1~7
        Date now = new Date();
        Record record = records[dayOfPeriod - 1];

        if (record.getId() == null) {
            model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.NOT_SIGN_IN, "您还未签到"));
            return;
        }

        // judge if have signed out or not signed in
        switch (periodOfDay) {
            case 0:
                if (record.getIn_time_mor() == null) { // not signed in already
                    model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.NOT_SIGN_IN, "您还未签到"));
                    return;
                }

                if (record.getOut_time_mor() != null) {
                    model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.HAVE_SIGN_OUT, "您已经签出"));
                    return;
                }
                break;
            case 1:
                if (record.getIn_time_noon() == null) { // not signed in already
                    model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.NOT_SIGN_IN, "您还未签到"));
                    return;
                }
                if (record.getOut_time_noon() != null) {
                    model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.HAVE_SIGN_OUT, "您已经签出"));
                    return;
                }
                break;
            case 2:
                if (record.getIn_time_eve() == null) { // not signed in already
                    model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.NOT_SIGN_IN, "您还未签到"));
                    return;
                }
                if (record.getOut_time_eve() != null) {
                    model.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.HAVE_SIGN_OUT, "您已经签出"));
                    return;
                }
                break;
            default:
                break;
        }

        Result result = recordService.signOut(record.getId().longValue(), now);
        if (result.getCode() == Code.SUCCESS) {
            getRecord(model, user, session);
            return;
        }

        model.addObject(Constants.ReqAttrs.ERROR, result);
    }
}
