package com.locatemybus.myorderbox.dto;

import java.util.List;

public class AbpError {
    public int Code;
    public String Message;
    public String Details;
    public List<AbpValidationError> ValidationErrors;
}
