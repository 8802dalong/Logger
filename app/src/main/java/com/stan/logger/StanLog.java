package com.stan.logger;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created by zhilong.guo on 2016/5/23.
 */
public class StanLog {
    private String TAG = null;
    private Context mContext;
    private boolean isShow = true;
    private boolean isWrite = false;
    /**
     * mTagList is used to store logs that had been used by {@link StanLog#write}
     */
    private List<String> mTagList=new ArrayList<String>();
    private static FileHandler fileHandler = null;

    public StanLog(String TAG, Context mContext, boolean isShow, boolean isWrite) {
        this.TAG = TAG;
        this.mContext = mContext;
        this.isShow = isShow;
        this.isWrite = isWrite;
    }
    public static void init(){
        initFileHandler();
    }
    public boolean isShow() {
        return isShow;
    }

    public boolean isWrite() {
        return isWrite;
    }
    public void write(String tag,String msd){
        if(fileHandler==null){
            initFileHandler();
        }
    }
    public void e(String tag, String msg, boolean isWrite) {
        if (isShow) {
            Log.e(tag, msg);
        }
        if (isWrite) {
            Logger logger = Logger.getLogger(tag);
            if(!mTagList.contains(tag)){
                mTagList.add(tag);
                logger.addHandler(fileHandler);
            }


        }
    }

    private static void initFileHandler() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {
                fileHandler = new FileHandler(Environment.getExternalStorageDirectory() + File.separator + "stan.log");
                fileHandler.setLevel(Level.INFO);
                Formatter formatter = new Formatter() {
                    @Override
                    public String format(LogRecord r) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        StringBuilder stringBuilder = new StringBuilder(format.format(new Date(r.getMillis())));
                        stringBuilder.append(" ");
                        stringBuilder.append(r.getSourceMethodName());
                        stringBuilder.append(" ");
                        stringBuilder.append(r.getLoggerName());
                        stringBuilder.append(": ");
                        stringBuilder.append(r.getMessage());
                        return stringBuilder.toString();
                    }
                };
                fileHandler.setFormatter(formatter);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
