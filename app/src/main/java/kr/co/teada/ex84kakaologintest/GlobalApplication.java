package kr.co.teada.ex84kakaologintest;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class GlobalApplication extends Application {

    //본인 참조변수 값의 정적변수
    static GlobalApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        if(instance==null) instance=this;

        //카카오 SDK와 앱의 Application 객체 연결 !! 초기화
        KakaoSDK.init(new GlobalApplication.KakaoSDKAdapter());

    }//end of onCreate()


        private static class KakaoSDKAdapter extends KakaoAdapter {
            /**
             * Session Config에 대해서는 default값들이 존재한다.
             * 필요한 상황에서만 override해서 사용하면 됨.
             * @return Session의 설정값.
             */

            @Override
            public ISessionConfig getSessionConfig() { //꼭할필요는 없음
                return new ISessionConfig() {
                    @Override
                    public AuthType[] getAuthTypes() {
                        return new AuthType[] {AuthType.KAKAO_ACCOUNT};
                    }

                    @Override
                    public boolean isUsingWebviewTimer() {
                        return false;
                    }

                    @Override
                    public boolean isSecureMode() {
                        return false;
                    }

                    @Override
                    public ApprovalType getApprovalType() {
                        return ApprovalType.INDIVIDUAL;
                    }

                    @Override
                    public boolean isSaveFormData() {
                        return true;
                    }
                };
            }

            @Override
            public IApplicationConfig getApplicationConfig() {
                return new IApplicationConfig() {
                    @Override
                    public Context getApplicationContext() {
                        return GlobalApplication.getGlobalApplicationContext();
                    }
                };//IApplicationConfig
            }//IApplicationConfig
        }

        private static Context getGlobalApplicationContext() {
            return  GlobalApplication.instance;
        }




    }//end of GlobalApplication
