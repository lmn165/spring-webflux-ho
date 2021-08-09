package com.ho.UserService.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
    public String now(){
        return new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss").format(new Date());
    }
}
