package com.honyar.hysdk;

import android.content.ContentResolver;
import android.util.Log;
import android.os.RemoteException;

import com.honyar.parameter.HYParameterInterface;
import com.honyar.hysytemservice.HYSystemServiceManager;
import android.net.Uri;
import android.content.Context;

/**
 * Time: 2022/6/9 11:07
 * Author: xukang
 * Des:  javadoc -d doc -encoding UTF-8 -charset UTF-8 HYSdk.java
 */

public class HYSdk {
    public static final String VERSION = "1.0";
    private static final String TAG = "HYSdk";
    private static HYSdk sInstance = null;
    private ContentResolver mResolver;
    Parameter param = null;
    HYServiceManager mHYServiceManager;

    private HYSdk(ContentResolver resolver) {
        mResolver = resolver;
    }

    public static HYSdk getInstance(ContentResolver resolver) {
        if (sInstance == null) {
            sInstance = new HYSdk(resolver);
        }
        return sInstance;
    }

    public HYServiceManager getHYServiceManager() {
        if (mHYServiceManager == null) {
            mHYServiceManager = new HYServiceManager();
        }
        return mHYServiceManager;
    }

    public class HYServiceManager {
        HYSystemServiceManager mHYSystemServiceManager;

        HYServiceManager() {
            mHYSystemServiceManager = new HYSystemServiceManager();
        }

        public int setSystemProperties(String key, String value) {
            int result = -1;
            result = mHYSystemServiceManager.setSystemProperties(key, value);
            return result;
        }

        public String getSystemProperties(String key, String defaultValue) {
            String result = "";
            result = mHYSystemServiceManager.getSystemProperties(key, defaultValue);
            return result;
        }

        public void shutDown() {
            mHYSystemServiceManager.shutDown();
        }

        public void reboot() {
            mHYSystemServiceManager.shutDown();
        }

        public int setSystemMute(boolean isMute) {
            return mHYSystemServiceManager.setSystemMute(isMute);
        }

        public int setTouchVoiceMute(boolean isMute) {
            return mHYSystemServiceManager.setTouchVoiceMute(isMute);
        }

        public int setSilentInstall(String apkFilePath) {
            return mHYSystemServiceManager.setSilentInstall(apkFilePath);
        }

        public int setInstallPackage(String packageFilePath) {
            return mHYSystemServiceManager.setInstallPackage(packageFilePath);
        }

        public String getFileMd5ToString(String filePath) {
            return mHYSystemServiceManager.getFileMd5ToString(filePath);
        }

        public void restoreFactorySettings(boolean mEraseSdCard, boolean mEraseEsims) {
            mHYSystemServiceManager.restoreFactorySettings(mEraseSdCard, mEraseEsims);
        }

        public void setGpio1Enable(int value) {
            mHYSystemServiceManager.setGpio1Enable(value);
        }

        public String getGpio1Status() {
            String result = "";
            result = mHYSystemServiceManager.getGpio1Status();
            return result;
        }

        public void setGpio2Enable(int value) {
            mHYSystemServiceManager.setGpio1Enable(value);
        }

        public String getGpio2Status() {
            String result = "";
            result = mHYSystemServiceManager.getGpio1Status();
            return result;
        }

        public void setLcdEnable(int value) {
            mHYSystemServiceManager.setLcdEnable(value);
        }

        public String getLcdStatus() {
            String result = "";
            result = mHYSystemServiceManager.getLcdStatus();
            return result;
        }

        //touchbar control
        public void setTouchbarLevel(int num, int brightness) {
            mHYSystemServiceManager.setTouchbarLevel(num, brightness);
        }

        //touchbar all control
        public void setAllTouchbarLevel(boolean isOpen) {
            if (isOpen) {
                mHYSystemServiceManager.setAllTouchbarLevel(255);
            } else {
                mHYSystemServiceManager.setAllTouchbarLevel(0);
            }
        }
    }

    public Parameter getParameter() {
        if (param == null) {
            param = new Parameter(mResolver);
        }
        return param;
    }

    public class Parameter {
        HYParameterInterface hypara;
        /**
         * 用户管理-用户名称配置.<br>
         * 使用接口可参考
         * {@link HYSdk#getParameterInterface()}.
         * <br>
         * <p>具体使用方式如下</p>
         * <li>setStrPara(HYSdk.Parameter.USER_MANAGER_NAME, "xiaoyan").
         * <li>getStrPara(HYSdk.Parameter.USER_MANAGER_NAME).
         */
        //用户昵称
        public static final String USER_MANAGER_NAME = HYParameterInterface.USER_MANAGER_NICKNAME;
        //用户使用时长
        public static final String USER_MANAGER_USEDATA = HYParameterInterface.USER_MANAGER_USEDATA;
        //用户ID
        public static final String USER_MANAGER_USERID = HYParameterInterface.USER_MANAGER_USERID;
        //用户手机号码
        public static final String USER_MANAGER_PHONENUM = HYParameterInterface.USER_MANAGER_PHONENUM;
        /**
         * 设置--屏幕设置
         */
        //屏幕亮度自适应调节是否开启 0：开启 1：关闭  默认均为1（开启）    以下switch类似处理方式
        public static final String SCREEN_LIGHT_AUTO_SWITCH = HYParameterInterface.SCREEN_LIGHT_AUTO_SWITCH;
        //屏幕亮度百分比  百分比均是按照100的处理方式，默认均为60%      以下percentage类似
        public static final String SCREEN_LIGHT_PERCENTAGE = HYParameterInterface.SCREEN_LIGHT_PERCENTAGE;
		 //在自动调节状态下屏幕亮度百分比  百分比均是按照100的处理方式，默认均为100%
        public static final String SCREEN_LIGHT_AUTO_ADJ_PERCENTAGE = HYParameterInterface.SCREEN_LIGHT_AUTO_ADJ_PERCENTAGE;
        //人来自动亮屏
        public static final String SCREEN_LIGHT_INDUCTION_SWITCH = HYParameterInterface.SCREEN_LIGHT_INDUCTION_SWITCH;
        /**
         * 设置--声音设置
         */
        //是否处于静音模式
        public static final String VOICE_MUTE_SWITCH = HYParameterInterface.VOICE_MUTE_SWITCH;
        //系统及通知音量
        public static final String VOICE_SYSTEM_PERCENTAGE = HYParameterInterface.VOICE_SYSTEM_PERCENTAGE;
        //多媒体的声音
        public static final String VOICE_MUSIC_PERCENTAGE = HYParameterInterface.VOICE_MUSIC_PERCENTAGE;
        //触摸提示音是否开启
        public static final String VOICE_TOUCH_SWITCH = HYParameterInterface.VOICE_TOUCH_SWITCH;
        //触摸提示音百分比
        public static final String VOICE_TOUCH_PERCENTAGE = HYParameterInterface.VOICE_TOUCH_PERCENTAGE;
        /**
         * 夜间模式
         */
        //夜间模式是否正常开启
        public static final String NIGHT_MODE_SWITCH = HYParameterInterface.NIGHT_MODE_SWITCH;
        //夜间模式开始时间
        public static final String NIGHT_MODE_STARE_TIME = HYParameterInterface.NIGHT_MODE_STARE_TIME;
        //夜间模式结束时间
        public static final String NIGHT_MODE_STOP_TIME = HYParameterInterface.NIGHT_MODE_STOP_TIME;
        /**
         * 小雁管家语音识别的设置
         */
        //小雁管家语音识别是否正常开启
        public static final String HONYAR_ASSISTANT_SWITCH = HYParameterInterface.HONYAR_ASSISTANT_SWITCH;
        //小雁管家语音识别 设置深聪模块的音量大小
        public static final String HONYAR_ASSISTANT_VOICE_PERCENTAGE = HYParameterInterface.HONYAR_ASSISTANT_VOICE_PERCENTAGE;
        //语音交互模式 1 Asr 语音交互模式 2 Voip通话降噪模式
        public static final String HONYAR_ASSISTANT_MODE_TYPE = HYParameterInterface.HONYAR_ASSISTANT_MODE_TYPE;
        //小雁管家语音识别  离线语言（实验室定制技能）
        public static final String HONYAR_ASSISTANT_OFFICE_SWITCH = HYParameterInterface.HONYAR_ASSISTANT_OFFICE_SWITCH;
        //小雁管家语音识别 离线指令词语 系统控制相关的 基于“:”分隔，进行词组的串联 screenLightUp:screenLightDown
        public static final String HONYAR_ASSISTANT_OFFICE_WORD_SYSTEM = HYParameterInterface.HONYAR_ASSISTANT_OFFICE_WORD_SYSTEM;
        //小雁管家语音识别 离线指令词语 设备控制相关的  基于“:”分隔，进行词组的串联 screenLightUp:screenLightDown
        public static final String HONYAR_ASSISTANT_OFFICE_WORD_DEVICE = HYParameterInterface.HONYAR_ASSISTANT_OFFICE_WORD_DEVICE;
        /**
         * 触控条设置
         */
        //触控条功能 是否正常开启
        public static final String TOUCHBAR_SWITCH = HYParameterInterface.TOUCHBAR_SWITCH;
        //触控条功能 控制的功能项 0：亮度控制  1：音量调节 2：灯光控制 3：窗帘控制 默认为 0:亮度控制
        public static final String TOUCHBAR_CONTROL_ITEM = HYParameterInterface.TOUCHBAR_CONTROL_ITEM;
        /**
         * 个性化锁屏
         */
        //个性化锁屏  锁屏时间
        public static final String SCRENN_TIME = HYParameterInterface.SCRENN_TIME;
        public static final String SCRENN_TIME_INDEX = HYParameterInterface.SCRENN_TIME_INDEX;
        //个性化锁屏  锁屏的样式 图片的路径/名称 该功能主要在SystemUI和系统设置中 注意数据的同步处理
        public static final String SCRENN_BG_PICTURE_INDEX = HYParameterInterface.SCRENN_BG_PICTURE_INDEX;
		 //是否处于锁屏显示状态
        public static final String SCRENN_BG_PICTURE_SHOW_SWITCH = HYParameterInterface.SCRENN_BG_PICTURE_SHOW_SWITCH;
        /**
         * 关于设备
         */
        //关于设备--设备详情--设备名称
        public static final String DEVICE_INFO_NAME = HYParameterInterface.DEVICE_INFO_NAME;
        //关于设备--设备详情--设备序列号
        public static final String DEVICE_INFO_SN = HYParameterInterface.DEVICE_INFO_SN;
        //关于设备--设备详情--软件版本
        public static final String DEVICE_INFO_VERSION = HYParameterInterface.DEVICE_INFO_VERSION;
        //关于设备--设备详情--MAC地址
        public static final String DEVICE_INFO_MAC = HYParameterInterface.DEVICE_INFO_MAC;
        //关于设备--系统更新--自动更新
        public static final String DEVICE_OTA_AUTO_SWITCH = HYParameterInterface.DEVICE_OTA_AUTO_SWITCH;
        //关于设备--系统更新--系统名称
        public static final String DEVICE_OTA_NAME = HYParameterInterface.DEVICE_OTA_NAME;
        //关于设备--系统更新--系统版本
        public static final String DEVICE_OTA_VERSION = HYParameterInterface.DEVICE_OTA_VERSION;
        //关于设备--系统更新--内核版本
        public static final String DEVICE_OTA_KERNEL_VERSION = HYParameterInterface.DEVICE_OTA_KERNEL_VERSION;;
        //关于设备--系统更新--上次更新时间
        public static final String DEVICE_OTA_LASTTIME = HYParameterInterface.DEVICE_OTA_LASTTIME;

        /**
         * 鸿雁系统特殊数据的设置
         */
        //是否为首次开机使用 默认是0 开机后为1
        public static final String FIRST_USE_DEVICE = HYParameterInterface.FIRST_USE_DEVICE;
        //是否禁止使用下拉状态栏 0禁止使用 1正常使用
        public static final String  FORBID_FLINNG_STATUSBAR= HYParameterInterface.FORBID_FLINNG_STATUSBAR;
		//设备的HomeID
        public static final String  DEVICE_HOMEID= HYParameterInterface.DEVICE_HOMEID;

        /**
         * 系统属性的参数配置
         */
        public static final String RO_HONYAR_PRODUCT_SERIALNO = "ro.honyar.product.serialno";//获取鸿雁的版本信息

        Parameter(ContentResolver resolver) {
            hypara = HYParameterInterface.getInstance();
            hypara.setContentResolver(resolver);
        }

        public boolean setStrPara(String name, String value) {
            return hypara.putStrPara(name, value);
        }

        public String getStrPara(String name) {
            return hypara.getStrPara(name);
        }

        public String getStrPara(String name, String defaultValue) {
            return hypara.getStrPara(name, defaultValue);
        }

        public boolean setIntPara(String name, int value) {
            return hypara.putIntPara(name, value);
        }

        public int getIntPara(String name) {
            return hypara.getIntPara(name);
        }

        public int getIntPara(String name, int defaultValue) {
            return hypara.getIntPara(name, defaultValue);
        }

        public boolean putLongPara(String name, long value) {
            return hypara.putLongPara(name, value);
        }

        public long getLongPara(String name) {
            return hypara.getLongPara(name);
        }

        public long getLongPara(String name, long value) {
            return hypara.getLongPara(name, value);
        }
        public  Uri getUriFor(String name) {
            return hypara.getUriFor(name);
        }
    }
}
