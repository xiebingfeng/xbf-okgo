package com.xbf.okgo.error;

import android.content.Context;

/**
 * @author xiebingfeng
 */
public interface IResponseErrorListener {

    void handleResponseError(Context context, Exception e);

}
