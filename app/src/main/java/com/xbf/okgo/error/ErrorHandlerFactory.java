package com.xbf.okgo.error;

import android.content.Context;


/**
 * Created by xbf on 2017/5/13.
 */
public class ErrorHandlerFactory {
    public final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private IResponseErrorListener mResponseErrorListener;

    public ErrorHandlerFactory(Context mContext, IResponseErrorListener mResponseErrorListener) {
        this.mContext = mContext;
        this.mResponseErrorListener = mResponseErrorListener;
    }

    /**
     * 处理错误
     *
     * @param throwable
     */
    public void handleError(Throwable throwable) {
        mResponseErrorListener.handleResponseError(mContext, (Exception) throwable);
    }

}
