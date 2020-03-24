package com.example.admin.railwayticketreservationsystem.select_buy_Tickets;

import android.os.CountDownTimer;
import android.widget.Button;

import com.example.admin.railwayticketreservationsystem.R;

public class TimeCount extends CountDownTimer {
    private Button btn_count;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCount(long millisInFuture, long countDownInterval,Button btn_count) {
        super(millisInFuture, countDownInterval);
        this.btn_count=btn_count;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        btn_count.setEnabled(false);
        btn_count.setBackgroundResource(R.color.colorPrimary);
        btn_count.setText(millisUntilFinished/1000+"秒");
    }

    @Override
    public void onFinish() {
        btn_count.setEnabled(true);
        btn_count.setText("获取验证码");
    }
}
