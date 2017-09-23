
package com.khilman.uploadimage;

import com.google.gson.annotations.SerializedName;

public class ResponseUpload {

    @SerializedName("filename")
    private String mFilename;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("result")
    private String mResult;

    public String getFilename() {
        return mFilename;
    }

    public void setFilename(String filename) {
        mFilename = filename;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
