package com.example.dr.library_app;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL= "http://dollyrijwaniwebhost2295.comli.com/Register.php";
    private Map<String,String> params;

    public RegisterRequest(String name, int roll_no, String branch, int year, String email, int phone, String username, String password, String cnfrmpass, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("roll_no",roll_no+"");
        params.put("branch",branch);
        params.put("year",year+"");
        params.put("email",email);
        params.put("phone",phone+"");
        params.put("username",username);
        params.put("password",password);
        params.put("cnfrmpass",cnfrmpass);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
