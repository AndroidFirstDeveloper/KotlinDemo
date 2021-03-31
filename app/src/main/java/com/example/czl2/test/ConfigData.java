package com.example.czl2.test;

import java.util.List;

public class ConfigData {


    /**
     * code : 10000
     * result : {"features":{"suirui":{"conference_robot_id":"XVzUGxuGMFPYe4v95NAlQ0n"},"qrcode":{"login_enable":true,"share_urls":["http://219.141.190.225/im/QR.html","https://219.141.190.225/im/QR.html"]},"eab":{"use_external_uid":false,"staff_no_required":true,"enable":true,"massive_mode":false,"mobile_required":false,"email_required":false,"username_binding":"staffNo","staff_user_compatibility_on":true},"privacy":{"display_mobile":true},"media":{"max_file_size":100,"upload_urls":["http://219.141.190.225:8086","https://219.141.190.225:8086"],"type":2,"download_urls":["http://219.141.190.225:8086","https://219.141.190.225:8086"]},"red_packet":{"enable":false},"robot":["E1XyIOJ4YxAaVWLSWUxOk0S","1cL4T7JbwSHHeVKPJ0Hjjsc","GbJUd9YggkHls6Zr8agEhkD"],"password":{"number":1,"symbol":0,"forgot_enable":false,"default_password_security_level":0,"min_char_type_count":1,"word":1,"symbol_scope":"~`@#$%^&*-_=+|?/()<>[]{}\",.;'!","minimum_length":6,"max_length":16},"security":{"password_public_key_rsa":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDFmPT0Bi+L46sHLzH/HlGKJlKFvPPB+oDkWrERLcXD65XqiujCHrWFZcBMITbzwWIWZfrw8BhcRz3kx1m+Gyl3EG/RNuQm4r7nAyLx2VmQoD56m722qRmq+0XDKv23ZzM1nWvAR9ZT8bJi3HmYqkXvVuXzAXhhL7X6EvUpY9mUQIDAQAB"},"file":{"file_transfer_robot_id":"jcTHDXIwjQ93EUqgUzXTsqD"},"moments":{"urls":["http://219.141.190.225/moments"],"enable":false,"url":"http://219.141.190.225/moments"},"pin":{"enable":true,"max_receiver_count":200,"max_attachment_count":9},"ldap_login":{"enable":false},"voip":{"video_enable":false,"audio_enable":false,"enable":false},"sms":{"verification_state":false},"friend":{"enable":true},"lockaccount":{"clear_local_data_enable":false},"emoticon":{"enable":true},"group":{"group_invite_expire_time":168,"group_invite_robot_id":"VDd2nBIBIAEjtfFs6XmMCC4"},"apm":{"android_bugly_id":"f60a819d2b","enable":true,"ios_bugly_id":"b8d4c6ee09"},"watermark":{"enable":true},"im":{"app_key":"sfci50a5lwp0i","stats_urls":["http://stats.cn.ronghub.com","https://stats.cn.ronghub.com"],"navi_urls":["http://219.141.190.225:8082","https://219.141.190.225:8082"],"media_urls":["http://219.141.190.225:8086","https://219.141.190.225:8086"]},"rcsr":{"config_format":"pcm","enable":false,"vendor":"xunfei","config_rate":16000,"config_channel":1,"xunfei_appid":"","config_bitdepth":16},"work":{"urls":["http://219.141.190.225/work/application.html","https://219.141.190.225/work/application.html"],"enable":true,"url":"http://219.141.190.225/work/application.html"},"voice_input":{"enable":true},"monitor":{"admin_operation_enable":true,"message_enable":true,"sensitive_word_enable":true,"device_enable":true,"conversation_enable":true},"push":{"customization_enable":true},"url":{"PC":"http://219.141.190.225/package/联和利泰 Setup 1.6.10.exe","ANDROID":"http://219.141.190.225/package/Litsoft_for_android_v1.0.apk","IOS":"http://d.7short.com/uwh3","MAC":"http://219.141.190.225/package/联和利泰-1.6.10.dmg"},"call":{"conference_display_number":1111111,"visitor_enable":false,"enable":false},"system":{"init":true},"remote_control":{"enable":false},"organization":{"use_external_uid":false,"staff_no_required":true,"enable":true,"massive_mode":false,"mobile_required":false,"email_required":false,"username_binding":"staffNo","staff_user_compatibility_on":true},"screenshot_watermark":{"enable":false},"registration":{"enable":false},"location":{"enable":true},"device":{"device_manage_robot_id":"Jytw2rWgizLXnONnif9SKWl"}},"expire":1800,"version":1614763646257}
     */

    private int code;
    /**
     * features : {"suirui":{"conference_robot_id":"XVzUGxuGMFPYe4v95NAlQ0n"},"qrcode":{"login_enable":true,"share_urls":["http://219.141.190.225/im/QR.html","https://219.141.190.225/im/QR.html"]},"eab":{"use_external_uid":false,"staff_no_required":true,"enable":true,"massive_mode":false,"mobile_required":false,"email_required":false,"username_binding":"staffNo","staff_user_compatibility_on":true},"privacy":{"display_mobile":true},"media":{"max_file_size":100,"upload_urls":["http://219.141.190.225:8086","https://219.141.190.225:8086"],"type":2,"download_urls":["http://219.141.190.225:8086","https://219.141.190.225:8086"]},"red_packet":{"enable":false},"robot":["E1XyIOJ4YxAaVWLSWUxOk0S","1cL4T7JbwSHHeVKPJ0Hjjsc","GbJUd9YggkHls6Zr8agEhkD"],"password":{"number":1,"symbol":0,"forgot_enable":false,"default_password_security_level":0,"min_char_type_count":1,"word":1,"symbol_scope":"~`@#$%^&*-_=+|?/()<>[]{}\",.;'!","minimum_length":6,"max_length":16},"security":{"password_public_key_rsa":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDFmPT0Bi+L46sHLzH/HlGKJlKFvPPB+oDkWrERLcXD65XqiujCHrWFZcBMITbzwWIWZfrw8BhcRz3kx1m+Gyl3EG/RNuQm4r7nAyLx2VmQoD56m722qRmq+0XDKv23ZzM1nWvAR9ZT8bJi3HmYqkXvVuXzAXhhL7X6EvUpY9mUQIDAQAB"},"file":{"file_transfer_robot_id":"jcTHDXIwjQ93EUqgUzXTsqD"},"moments":{"urls":["http://219.141.190.225/moments"],"enable":false,"url":"http://219.141.190.225/moments"},"pin":{"enable":true,"max_receiver_count":200,"max_attachment_count":9},"ldap_login":{"enable":false},"voip":{"video_enable":false,"audio_enable":false,"enable":false},"sms":{"verification_state":false},"friend":{"enable":true},"lockaccount":{"clear_local_data_enable":false},"emoticon":{"enable":true},"group":{"group_invite_expire_time":168,"group_invite_robot_id":"VDd2nBIBIAEjtfFs6XmMCC4"},"apm":{"android_bugly_id":"f60a819d2b","enable":true,"ios_bugly_id":"b8d4c6ee09"},"watermark":{"enable":true},"im":{"app_key":"sfci50a5lwp0i","stats_urls":["http://stats.cn.ronghub.com","https://stats.cn.ronghub.com"],"navi_urls":["http://219.141.190.225:8082","https://219.141.190.225:8082"],"media_urls":["http://219.141.190.225:8086","https://219.141.190.225:8086"]},"rcsr":{"config_format":"pcm","enable":false,"vendor":"xunfei","config_rate":16000,"config_channel":1,"xunfei_appid":"","config_bitdepth":16},"work":{"urls":["http://219.141.190.225/work/application.html","https://219.141.190.225/work/application.html"],"enable":true,"url":"http://219.141.190.225/work/application.html"},"voice_input":{"enable":true},"monitor":{"admin_operation_enable":true,"message_enable":true,"sensitive_word_enable":true,"device_enable":true,"conversation_enable":true},"push":{"customization_enable":true},"url":{"PC":"http://219.141.190.225/package/联和利泰 Setup 1.6.10.exe","ANDROID":"http://219.141.190.225/package/Litsoft_for_android_v1.0.apk","IOS":"http://d.7short.com/uwh3","MAC":"http://219.141.190.225/package/联和利泰-1.6.10.dmg"},"call":{"conference_display_number":1111111,"visitor_enable":false,"enable":false},"system":{"init":true},"remote_control":{"enable":false},"organization":{"use_external_uid":false,"staff_no_required":true,"enable":true,"massive_mode":false,"mobile_required":false,"email_required":false,"username_binding":"staffNo","staff_user_compatibility_on":true},"screenshot_watermark":{"enable":false},"registration":{"enable":false},"location":{"enable":true},"device":{"device_manage_robot_id":"Jytw2rWgizLXnONnif9SKWl"}}
     * expire : 1800
     * version : 1614763646257
     */

    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * suirui : {"conference_robot_id":"XVzUGxuGMFPYe4v95NAlQ0n"}
         * qrcode : {"login_enable":true,"share_urls":["http://219.141.190.225/im/QR.html","https://219.141.190.225/im/QR.html"]}
         * eab : {"use_external_uid":false,"staff_no_required":true,"enable":true,"massive_mode":false,"mobile_required":false,"email_required":false,"username_binding":"staffNo","staff_user_compatibility_on":true}
         * privacy : {"display_mobile":true}
         * media : {"max_file_size":100,"upload_urls":["http://219.141.190.225:8086","https://219.141.190.225:8086"],"type":2,"download_urls":["http://219.141.190.225:8086","https://219.141.190.225:8086"]}
         * red_packet : {"enable":false}
         * robot : ["E1XyIOJ4YxAaVWLSWUxOk0S","1cL4T7JbwSHHeVKPJ0Hjjsc","GbJUd9YggkHls6Zr8agEhkD"]
         * password : {"number":1,"symbol":0,"forgot_enable":false,"default_password_security_level":0,"min_char_type_count":1,"word":1,"symbol_scope":"~`@#$%^&*-_=+|?/()<>[]{}\",.;'!","minimum_length":6,"max_length":16}
         * security : {"password_public_key_rsa":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDFmPT0Bi+L46sHLzH/HlGKJlKFvPPB+oDkWrERLcXD65XqiujCHrWFZcBMITbzwWIWZfrw8BhcRz3kx1m+Gyl3EG/RNuQm4r7nAyLx2VmQoD56m722qRmq+0XDKv23ZzM1nWvAR9ZT8bJi3HmYqkXvVuXzAXhhL7X6EvUpY9mUQIDAQAB"}
         * file : {"file_transfer_robot_id":"jcTHDXIwjQ93EUqgUzXTsqD"}
         * moments : {"urls":["http://219.141.190.225/moments"],"enable":false,"url":"http://219.141.190.225/moments"}
         * pin : {"enable":true,"max_receiver_count":200,"max_attachment_count":9}
         * ldap_login : {"enable":false}
         * voip : {"video_enable":false,"audio_enable":false,"enable":false}
         * sms : {"verification_state":false}
         * friend : {"enable":true}
         * lockaccount : {"clear_local_data_enable":false}
         * emoticon : {"enable":true}
         * group : {"group_invite_expire_time":168,"group_invite_robot_id":"VDd2nBIBIAEjtfFs6XmMCC4"}
         * apm : {"android_bugly_id":"f60a819d2b","enable":true,"ios_bugly_id":"b8d4c6ee09"}
         * watermark : {"enable":true}
         * im : {"app_key":"sfci50a5lwp0i","stats_urls":["http://stats.cn.ronghub.com","https://stats.cn.ronghub.com"],"navi_urls":["http://219.141.190.225:8082","https://219.141.190.225:8082"],"media_urls":["http://219.141.190.225:8086","https://219.141.190.225:8086"]}
         * rcsr : {"config_format":"pcm","enable":false,"vendor":"xunfei","config_rate":16000,"config_channel":1,"xunfei_appid":"","config_bitdepth":16}
         * work : {"urls":["http://219.141.190.225/work/application.html","https://219.141.190.225/work/application.html"],"enable":true,"url":"http://219.141.190.225/work/application.html"}
         * voice_input : {"enable":true}
         * monitor : {"admin_operation_enable":true,"message_enable":true,"sensitive_word_enable":true,"device_enable":true,"conversation_enable":true}
         * push : {"customization_enable":true}
         * url : {"PC":"http://219.141.190.225/package/联和利泰 Setup 1.6.10.exe","ANDROID":"http://219.141.190.225/package/Litsoft_for_android_v1.0.apk","IOS":"http://d.7short.com/uwh3","MAC":"http://219.141.190.225/package/联和利泰-1.6.10.dmg"}
         * call : {"conference_display_number":1111111,"visitor_enable":false,"enable":false}
         * system : {"init":true}
         * remote_control : {"enable":false}
         * organization : {"use_external_uid":false,"staff_no_required":true,"enable":true,"massive_mode":false,"mobile_required":false,"email_required":false,"username_binding":"staffNo","staff_user_compatibility_on":true}
         * screenshot_watermark : {"enable":false}
         * registration : {"enable":false}
         * location : {"enable":true}
         * device : {"device_manage_robot_id":"Jytw2rWgizLXnONnif9SKWl"}
         */

        private FeaturesBean features;
        private int expire;
        private long version;

        public FeaturesBean getFeatures() {
            return features;
        }

        public void setFeatures(FeaturesBean features) {
            this.features = features;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public long getVersion() {
            return version;
        }

        public void setVersion(long version) {
            this.version = version;
        }

        public static class FeaturesBean {
            /**
             * conference_robot_id : XVzUGxuGMFPYe4v95NAlQ0n
             */

            private SuiruiBean suirui;
            /**
             * login_enable : true
             * share_urls : ["http://219.141.190.225/im/QR.html","https://219.141.190.225/im/QR.html"]
             */

            private QrcodeBean qrcode;
            /**
             * use_external_uid : false
             * staff_no_required : true
             * enable : true
             * massive_mode : false
             * mobile_required : false
             * email_required : false
             * username_binding : staffNo
             * staff_user_compatibility_on : true
             */

            private EabBean eab;
            /**
             * display_mobile : true
             */

            private PrivacyBean privacy;
            /**
             * max_file_size : 100
             * upload_urls : ["http://219.141.190.225:8086","https://219.141.190.225:8086"]
             * type : 2
             * download_urls : ["http://219.141.190.225:8086","https://219.141.190.225:8086"]
             */

            private MediaBean media;
            /**
             * enable : false
             */

            private RedPacketBean red_packet;
            /**
             * number : 1
             * symbol : 0
             * forgot_enable : false
             * default_password_security_level : 0
             * min_char_type_count : 1
             * word : 1
             * symbol_scope : ~`@#$%^&*-_=+|?/()<>[]{}",.;'!
             * minimum_length : 6
             * max_length : 16
             */

            private PasswordBean password;
            /**
             * password_public_key_rsa : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDFmPT0Bi+L46sHLzH/HlGKJlKFvPPB+oDkWrERLcXD65XqiujCHrWFZcBMITbzwWIWZfrw8BhcRz3kx1m+Gyl3EG/RNuQm4r7nAyLx2VmQoD56m722qRmq+0XDKv23ZzM1nWvAR9ZT8bJi3HmYqkXvVuXzAXhhL7X6EvUpY9mUQIDAQAB
             */

            private SecurityBean security;
            /**
             * file_transfer_robot_id : jcTHDXIwjQ93EUqgUzXTsqD
             */

            private FileBean file;
            /**
             * urls : ["http://219.141.190.225/moments"]
             * enable : false
             * url : http://219.141.190.225/moments
             */

            private MomentsBean moments;
            /**
             * enable : true
             * max_receiver_count : 200
             * max_attachment_count : 9
             */

            private PinBean pin;
            /**
             * enable : false
             */

            private LdapLoginBean ldap_login;
            /**
             * video_enable : false
             * audio_enable : false
             * enable : false
             */

            private VoipBean voip;
            /**
             * verification_state : false
             */

            private SmsBean sms;
            /**
             * enable : true
             */

            private FriendBean friend;
            /**
             * clear_local_data_enable : false
             */

            private LockaccountBean lockaccount;
            /**
             * enable : true
             */

            private EmoticonBean emoticon;
            /**
             * group_invite_expire_time : 168
             * group_invite_robot_id : VDd2nBIBIAEjtfFs6XmMCC4
             */

            private GroupBean group;
            /**
             * android_bugly_id : f60a819d2b
             * enable : true
             * ios_bugly_id : b8d4c6ee09
             */

            private ApmBean apm;
            /**
             * enable : true
             */

            private WatermarkBean watermark;
            /**
             * app_key : sfci50a5lwp0i
             * stats_urls : ["http://stats.cn.ronghub.com","https://stats.cn.ronghub.com"]
             * navi_urls : ["http://219.141.190.225:8082","https://219.141.190.225:8082"]
             * media_urls : ["http://219.141.190.225:8086","https://219.141.190.225:8086"]
             */

            private ImBean im;
            /**
             * config_format : pcm
             * enable : false
             * vendor : xunfei
             * config_rate : 16000
             * config_channel : 1
             * xunfei_appid :
             * config_bitdepth : 16
             */

            private RcsrBean rcsr;
            /**
             * urls : ["http://219.141.190.225/work/application.html","https://219.141.190.225/work/application.html"]
             * enable : true
             * url : http://219.141.190.225/work/application.html
             */

            private WorkBean work;
            /**
             * enable : true
             */

            private VoiceInputBean voice_input;
            /**
             * admin_operation_enable : true
             * message_enable : true
             * sensitive_word_enable : true
             * device_enable : true
             * conversation_enable : true
             */

            private MonitorBean monitor;
            /**
             * customization_enable : true
             */

            private PushBean push;
            /**
             * PC : http://219.141.190.225/package/联和利泰 Setup 1.6.10.exe
             * ANDROID : http://219.141.190.225/package/Litsoft_for_android_v1.0.apk
             * IOS : http://d.7short.com/uwh3
             * MAC : http://219.141.190.225/package/联和利泰-1.6.10.dmg
             */

            private UrlBean url;
            /**
             * conference_display_number : 1111111
             * visitor_enable : false
             * enable : false
             */

            private CallBean call;
            /**
             * init : true
             */

            private SystemBean system;
            /**
             * enable : false
             */

            private RemoteControlBean remote_control;
            /**
             * use_external_uid : false
             * staff_no_required : true
             * enable : true
             * massive_mode : false
             * mobile_required : false
             * email_required : false
             * username_binding : staffNo
             * staff_user_compatibility_on : true
             */

            private OrganizationBean organization;
            /**
             * enable : false
             */

            private ScreenshotWatermarkBean screenshot_watermark;
            /**
             * enable : false
             */

            private RegistrationBean registration;
            /**
             * enable : true
             */

            private LocationBean location;
            /**
             * device_manage_robot_id : Jytw2rWgizLXnONnif9SKWl
             */

            private DeviceBean device;
            private List<String> robot;

            public SuiruiBean getSuirui() {
                return suirui;
            }

            public void setSuirui(SuiruiBean suirui) {
                this.suirui = suirui;
            }

            public QrcodeBean getQrcode() {
                return qrcode;
            }

            public void setQrcode(QrcodeBean qrcode) {
                this.qrcode = qrcode;
            }

            public EabBean getEab() {
                return eab;
            }

            public void setEab(EabBean eab) {
                this.eab = eab;
            }

            public PrivacyBean getPrivacy() {
                return privacy;
            }

            public void setPrivacy(PrivacyBean privacy) {
                this.privacy = privacy;
            }

            public MediaBean getMedia() {
                return media;
            }

            public void setMedia(MediaBean media) {
                this.media = media;
            }

            public RedPacketBean getRed_packet() {
                return red_packet;
            }

            public void setRed_packet(RedPacketBean red_packet) {
                this.red_packet = red_packet;
            }

            public PasswordBean getPassword() {
                return password;
            }

            public void setPassword(PasswordBean password) {
                this.password = password;
            }

            public SecurityBean getSecurity() {
                return security;
            }

            public void setSecurity(SecurityBean security) {
                this.security = security;
            }

            public FileBean getFile() {
                return file;
            }

            public void setFile(FileBean file) {
                this.file = file;
            }

            public MomentsBean getMoments() {
                return moments;
            }

            public void setMoments(MomentsBean moments) {
                this.moments = moments;
            }

            public PinBean getPin() {
                return pin;
            }

            public void setPin(PinBean pin) {
                this.pin = pin;
            }

            public LdapLoginBean getLdap_login() {
                return ldap_login;
            }

            public void setLdap_login(LdapLoginBean ldap_login) {
                this.ldap_login = ldap_login;
            }

            public VoipBean getVoip() {
                return voip;
            }

            public void setVoip(VoipBean voip) {
                this.voip = voip;
            }

            public SmsBean getSms() {
                return sms;
            }

            public void setSms(SmsBean sms) {
                this.sms = sms;
            }

            public FriendBean getFriend() {
                return friend;
            }

            public void setFriend(FriendBean friend) {
                this.friend = friend;
            }

            public LockaccountBean getLockaccount() {
                return lockaccount;
            }

            public void setLockaccount(LockaccountBean lockaccount) {
                this.lockaccount = lockaccount;
            }

            public EmoticonBean getEmoticon() {
                return emoticon;
            }

            public void setEmoticon(EmoticonBean emoticon) {
                this.emoticon = emoticon;
            }

            public GroupBean getGroup() {
                return group;
            }

            public void setGroup(GroupBean group) {
                this.group = group;
            }

            public ApmBean getApm() {
                return apm;
            }

            public void setApm(ApmBean apm) {
                this.apm = apm;
            }

            public WatermarkBean getWatermark() {
                return watermark;
            }

            public void setWatermark(WatermarkBean watermark) {
                this.watermark = watermark;
            }

            public ImBean getIm() {
                return im;
            }

            public void setIm(ImBean im) {
                this.im = im;
            }

            public RcsrBean getRcsr() {
                return rcsr;
            }

            public void setRcsr(RcsrBean rcsr) {
                this.rcsr = rcsr;
            }

            public WorkBean getWork() {
                return work;
            }

            public void setWork(WorkBean work) {
                this.work = work;
            }

            public VoiceInputBean getVoice_input() {
                return voice_input;
            }

            public void setVoice_input(VoiceInputBean voice_input) {
                this.voice_input = voice_input;
            }

            public MonitorBean getMonitor() {
                return monitor;
            }

            public void setMonitor(MonitorBean monitor) {
                this.monitor = monitor;
            }

            public PushBean getPush() {
                return push;
            }

            public void setPush(PushBean push) {
                this.push = push;
            }

            public UrlBean getUrl() {
                return url;
            }

            public void setUrl(UrlBean url) {
                this.url = url;
            }

            public CallBean getCall() {
                return call;
            }

            public void setCall(CallBean call) {
                this.call = call;
            }

            public SystemBean getSystem() {
                return system;
            }

            public void setSystem(SystemBean system) {
                this.system = system;
            }

            public RemoteControlBean getRemote_control() {
                return remote_control;
            }

            public void setRemote_control(RemoteControlBean remote_control) {
                this.remote_control = remote_control;
            }

            public OrganizationBean getOrganization() {
                return organization;
            }

            public void setOrganization(OrganizationBean organization) {
                this.organization = organization;
            }

            public ScreenshotWatermarkBean getScreenshot_watermark() {
                return screenshot_watermark;
            }

            public void setScreenshot_watermark(ScreenshotWatermarkBean screenshot_watermark) {
                this.screenshot_watermark = screenshot_watermark;
            }

            public RegistrationBean getRegistration() {
                return registration;
            }

            public void setRegistration(RegistrationBean registration) {
                this.registration = registration;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public DeviceBean getDevice() {
                return device;
            }

            public void setDevice(DeviceBean device) {
                this.device = device;
            }

            public List<String> getRobot() {
                return robot;
            }

            public void setRobot(List<String> robot) {
                this.robot = robot;
            }

            public static class SuiruiBean {
                private String conference_robot_id;

                public String getConference_robot_id() {
                    return conference_robot_id;
                }

                public void setConference_robot_id(String conference_robot_id) {
                    this.conference_robot_id = conference_robot_id;
                }
            }

            public static class QrcodeBean {
                private boolean login_enable;
                private List<String> share_urls;

                public boolean isLogin_enable() {
                    return login_enable;
                }

                public void setLogin_enable(boolean login_enable) {
                    this.login_enable = login_enable;
                }

                public List<String> getShare_urls() {
                    return share_urls;
                }

                public void setShare_urls(List<String> share_urls) {
                    this.share_urls = share_urls;
                }
            }

            public static class EabBean {
                private boolean use_external_uid;
                private boolean staff_no_required;
                private boolean enable;
                private boolean massive_mode;
                private boolean mobile_required;
                private boolean email_required;
                private String username_binding;
                private boolean staff_user_compatibility_on;

                public boolean isUse_external_uid() {
                    return use_external_uid;
                }

                public void setUse_external_uid(boolean use_external_uid) {
                    this.use_external_uid = use_external_uid;
                }

                public boolean isStaff_no_required() {
                    return staff_no_required;
                }

                public void setStaff_no_required(boolean staff_no_required) {
                    this.staff_no_required = staff_no_required;
                }

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }

                public boolean isMassive_mode() {
                    return massive_mode;
                }

                public void setMassive_mode(boolean massive_mode) {
                    this.massive_mode = massive_mode;
                }

                public boolean isMobile_required() {
                    return mobile_required;
                }

                public void setMobile_required(boolean mobile_required) {
                    this.mobile_required = mobile_required;
                }

                public boolean isEmail_required() {
                    return email_required;
                }

                public void setEmail_required(boolean email_required) {
                    this.email_required = email_required;
                }

                public String getUsername_binding() {
                    return username_binding;
                }

                public void setUsername_binding(String username_binding) {
                    this.username_binding = username_binding;
                }

                public boolean isStaff_user_compatibility_on() {
                    return staff_user_compatibility_on;
                }

                public void setStaff_user_compatibility_on(boolean staff_user_compatibility_on) {
                    this.staff_user_compatibility_on = staff_user_compatibility_on;
                }
            }

            public static class PrivacyBean {
                private boolean display_mobile;

                public boolean isDisplay_mobile() {
                    return display_mobile;
                }

                public void setDisplay_mobile(boolean display_mobile) {
                    this.display_mobile = display_mobile;
                }
            }

            public static class MediaBean {
                private int max_file_size;
                private int type;
                private List<String> upload_urls;
                private List<String> download_urls;

                public int getMax_file_size() {
                    return max_file_size;
                }

                public void setMax_file_size(int max_file_size) {
                    this.max_file_size = max_file_size;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public List<String> getUpload_urls() {
                    return upload_urls;
                }

                public void setUpload_urls(List<String> upload_urls) {
                    this.upload_urls = upload_urls;
                }

                public List<String> getDownload_urls() {
                    return download_urls;
                }

                public void setDownload_urls(List<String> download_urls) {
                    this.download_urls = download_urls;
                }
            }

            public static class RedPacketBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class PasswordBean {
                private int number;
                private int symbol;
                private boolean forgot_enable;
                private int default_password_security_level;
                private int min_char_type_count;
                private int word;
                private String symbol_scope;
                private int minimum_length;
                private int max_length;

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public int getSymbol() {
                    return symbol;
                }

                public void setSymbol(int symbol) {
                    this.symbol = symbol;
                }

                public boolean isForgot_enable() {
                    return forgot_enable;
                }

                public void setForgot_enable(boolean forgot_enable) {
                    this.forgot_enable = forgot_enable;
                }

                public int getDefault_password_security_level() {
                    return default_password_security_level;
                }

                public void setDefault_password_security_level(int default_password_security_level) {
                    this.default_password_security_level = default_password_security_level;
                }

                public int getMin_char_type_count() {
                    return min_char_type_count;
                }

                public void setMin_char_type_count(int min_char_type_count) {
                    this.min_char_type_count = min_char_type_count;
                }

                public int getWord() {
                    return word;
                }

                public void setWord(int word) {
                    this.word = word;
                }

                public String getSymbol_scope() {
                    return symbol_scope;
                }

                public void setSymbol_scope(String symbol_scope) {
                    this.symbol_scope = symbol_scope;
                }

                public int getMinimum_length() {
                    return minimum_length;
                }

                public void setMinimum_length(int minimum_length) {
                    this.minimum_length = minimum_length;
                }

                public int getMax_length() {
                    return max_length;
                }

                public void setMax_length(int max_length) {
                    this.max_length = max_length;
                }
            }

            public static class SecurityBean {
                private String password_public_key_rsa;

                public String getPassword_public_key_rsa() {
                    return password_public_key_rsa;
                }

                public void setPassword_public_key_rsa(String password_public_key_rsa) {
                    this.password_public_key_rsa = password_public_key_rsa;
                }
            }

            public static class FileBean {
                private String file_transfer_robot_id;

                public String getFile_transfer_robot_id() {
                    return file_transfer_robot_id;
                }

                public void setFile_transfer_robot_id(String file_transfer_robot_id) {
                    this.file_transfer_robot_id = file_transfer_robot_id;
                }
            }

            public static class MomentsBean {
                private boolean enable;
                private String url;
                private List<String> urls;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public List<String> getUrls() {
                    return urls;
                }

                public void setUrls(List<String> urls) {
                    this.urls = urls;
                }
            }

            public static class PinBean {
                private boolean enable;
                private int max_receiver_count;
                private int max_attachment_count;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }

                public int getMax_receiver_count() {
                    return max_receiver_count;
                }

                public void setMax_receiver_count(int max_receiver_count) {
                    this.max_receiver_count = max_receiver_count;
                }

                public int getMax_attachment_count() {
                    return max_attachment_count;
                }

                public void setMax_attachment_count(int max_attachment_count) {
                    this.max_attachment_count = max_attachment_count;
                }
            }

            public static class LdapLoginBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class VoipBean {
                private boolean video_enable;
                private boolean audio_enable;
                private boolean enable;

                public boolean isVideo_enable() {
                    return video_enable;
                }

                public void setVideo_enable(boolean video_enable) {
                    this.video_enable = video_enable;
                }

                public boolean isAudio_enable() {
                    return audio_enable;
                }

                public void setAudio_enable(boolean audio_enable) {
                    this.audio_enable = audio_enable;
                }

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class SmsBean {
                private boolean verification_state;

                public boolean isVerification_state() {
                    return verification_state;
                }

                public void setVerification_state(boolean verification_state) {
                    this.verification_state = verification_state;
                }
            }

            public static class FriendBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class LockaccountBean {
                private boolean clear_local_data_enable;

                public boolean isClear_local_data_enable() {
                    return clear_local_data_enable;
                }

                public void setClear_local_data_enable(boolean clear_local_data_enable) {
                    this.clear_local_data_enable = clear_local_data_enable;
                }
            }

            public static class EmoticonBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class GroupBean {
                private int group_invite_expire_time;
                private String group_invite_robot_id;

                public int getGroup_invite_expire_time() {
                    return group_invite_expire_time;
                }

                public void setGroup_invite_expire_time(int group_invite_expire_time) {
                    this.group_invite_expire_time = group_invite_expire_time;
                }

                public String getGroup_invite_robot_id() {
                    return group_invite_robot_id;
                }

                public void setGroup_invite_robot_id(String group_invite_robot_id) {
                    this.group_invite_robot_id = group_invite_robot_id;
                }
            }

            public static class ApmBean {
                private String android_bugly_id;
                private boolean enable;
                private String ios_bugly_id;

                public String getAndroid_bugly_id() {
                    return android_bugly_id;
                }

                public void setAndroid_bugly_id(String android_bugly_id) {
                    this.android_bugly_id = android_bugly_id;
                }

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }

                public String getIos_bugly_id() {
                    return ios_bugly_id;
                }

                public void setIos_bugly_id(String ios_bugly_id) {
                    this.ios_bugly_id = ios_bugly_id;
                }
            }

            public static class WatermarkBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class ImBean {
                private String app_key;
                private List<String> stats_urls;
                private List<String> navi_urls;
                private List<String> media_urls;

                public String getApp_key() {
                    return app_key;
                }

                public void setApp_key(String app_key) {
                    this.app_key = app_key;
                }

                public List<String> getStats_urls() {
                    return stats_urls;
                }

                public void setStats_urls(List<String> stats_urls) {
                    this.stats_urls = stats_urls;
                }

                public List<String> getNavi_urls() {
                    return navi_urls;
                }

                public void setNavi_urls(List<String> navi_urls) {
                    this.navi_urls = navi_urls;
                }

                public List<String> getMedia_urls() {
                    return media_urls;
                }

                public void setMedia_urls(List<String> media_urls) {
                    this.media_urls = media_urls;
                }
            }

            public static class RcsrBean {
                private String config_format;
                private boolean enable;
                private String vendor;
                private int config_rate;
                private int config_channel;
                private String xunfei_appid;
                private int config_bitdepth;

                public String getConfig_format() {
                    return config_format;
                }

                public void setConfig_format(String config_format) {
                    this.config_format = config_format;
                }

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }

                public String getVendor() {
                    return vendor;
                }

                public void setVendor(String vendor) {
                    this.vendor = vendor;
                }

                public int getConfig_rate() {
                    return config_rate;
                }

                public void setConfig_rate(int config_rate) {
                    this.config_rate = config_rate;
                }

                public int getConfig_channel() {
                    return config_channel;
                }

                public void setConfig_channel(int config_channel) {
                    this.config_channel = config_channel;
                }

                public String getXunfei_appid() {
                    return xunfei_appid;
                }

                public void setXunfei_appid(String xunfei_appid) {
                    this.xunfei_appid = xunfei_appid;
                }

                public int getConfig_bitdepth() {
                    return config_bitdepth;
                }

                public void setConfig_bitdepth(int config_bitdepth) {
                    this.config_bitdepth = config_bitdepth;
                }
            }

            public static class WorkBean {
                private boolean enable;
                private String url;
                private List<String> urls;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public List<String> getUrls() {
                    return urls;
                }

                public void setUrls(List<String> urls) {
                    this.urls = urls;
                }
            }

            public static class VoiceInputBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class MonitorBean {
                private boolean admin_operation_enable;
                private boolean message_enable;
                private boolean sensitive_word_enable;
                private boolean device_enable;
                private boolean conversation_enable;

                public boolean isAdmin_operation_enable() {
                    return admin_operation_enable;
                }

                public void setAdmin_operation_enable(boolean admin_operation_enable) {
                    this.admin_operation_enable = admin_operation_enable;
                }

                public boolean isMessage_enable() {
                    return message_enable;
                }

                public void setMessage_enable(boolean message_enable) {
                    this.message_enable = message_enable;
                }

                public boolean isSensitive_word_enable() {
                    return sensitive_word_enable;
                }

                public void setSensitive_word_enable(boolean sensitive_word_enable) {
                    this.sensitive_word_enable = sensitive_word_enable;
                }

                public boolean isDevice_enable() {
                    return device_enable;
                }

                public void setDevice_enable(boolean device_enable) {
                    this.device_enable = device_enable;
                }

                public boolean isConversation_enable() {
                    return conversation_enable;
                }

                public void setConversation_enable(boolean conversation_enable) {
                    this.conversation_enable = conversation_enable;
                }
            }

            public static class PushBean {
                private boolean customization_enable;

                public boolean isCustomization_enable() {
                    return customization_enable;
                }

                public void setCustomization_enable(boolean customization_enable) {
                    this.customization_enable = customization_enable;
                }
            }

            public static class UrlBean {
                private String PC;
                private String ANDROID;
                private String IOS;
                private String MAC;

                public String getPC() {
                    return PC;
                }

                public void setPC(String PC) {
                    this.PC = PC;
                }

                public String getANDROID() {
                    return ANDROID;
                }

                public void setANDROID(String ANDROID) {
                    this.ANDROID = ANDROID;
                }

                public String getIOS() {
                    return IOS;
                }

                public void setIOS(String IOS) {
                    this.IOS = IOS;
                }

                public String getMAC() {
                    return MAC;
                }

                public void setMAC(String MAC) {
                    this.MAC = MAC;
                }
            }

            public static class CallBean {
                private int conference_display_number;
                private boolean visitor_enable;
                private boolean enable;

                public int getConference_display_number() {
                    return conference_display_number;
                }

                public void setConference_display_number(int conference_display_number) {
                    this.conference_display_number = conference_display_number;
                }

                public boolean isVisitor_enable() {
                    return visitor_enable;
                }

                public void setVisitor_enable(boolean visitor_enable) {
                    this.visitor_enable = visitor_enable;
                }

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class SystemBean {
                private boolean init;

                public boolean isInit() {
                    return init;
                }

                public void setInit(boolean init) {
                    this.init = init;
                }
            }

            public static class RemoteControlBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class OrganizationBean {
                private boolean use_external_uid;
                private boolean staff_no_required;
                private boolean enable;
                private boolean massive_mode;
                private boolean mobile_required;
                private boolean email_required;
                private String username_binding;
                private boolean staff_user_compatibility_on;

                public boolean isUse_external_uid() {
                    return use_external_uid;
                }

                public void setUse_external_uid(boolean use_external_uid) {
                    this.use_external_uid = use_external_uid;
                }

                public boolean isStaff_no_required() {
                    return staff_no_required;
                }

                public void setStaff_no_required(boolean staff_no_required) {
                    this.staff_no_required = staff_no_required;
                }

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }

                public boolean isMassive_mode() {
                    return massive_mode;
                }

                public void setMassive_mode(boolean massive_mode) {
                    this.massive_mode = massive_mode;
                }

                public boolean isMobile_required() {
                    return mobile_required;
                }

                public void setMobile_required(boolean mobile_required) {
                    this.mobile_required = mobile_required;
                }

                public boolean isEmail_required() {
                    return email_required;
                }

                public void setEmail_required(boolean email_required) {
                    this.email_required = email_required;
                }

                public String getUsername_binding() {
                    return username_binding;
                }

                public void setUsername_binding(String username_binding) {
                    this.username_binding = username_binding;
                }

                public boolean isStaff_user_compatibility_on() {
                    return staff_user_compatibility_on;
                }

                public void setStaff_user_compatibility_on(boolean staff_user_compatibility_on) {
                    this.staff_user_compatibility_on = staff_user_compatibility_on;
                }
            }

            public static class ScreenshotWatermarkBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class RegistrationBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class LocationBean {
                private boolean enable;

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class DeviceBean {
                private String device_manage_robot_id;

                public String getDevice_manage_robot_id() {
                    return device_manage_robot_id;
                }

                public void setDevice_manage_robot_id(String device_manage_robot_id) {
                    this.device_manage_robot_id = device_manage_robot_id;
                }
            }
        }
    }
}
