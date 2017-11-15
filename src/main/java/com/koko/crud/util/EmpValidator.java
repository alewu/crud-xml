//package com.koko.crud.util;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//public class EmpValidator implements Validator {
//    /**
//     * This Validator validates *just* Emp instances
//     */
//    public boolean supports(Class<?> clazz) {
//        return Emp.class.equals(clazz);
//    }
//
//    public void validate(Object obj, Errors e) {
//        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
//        Emp p = (Emp) obj;
//        if (p.getEmail().equals("")){
//            e.reject("400","邮箱格式有误");
//        }
//    }
//}
