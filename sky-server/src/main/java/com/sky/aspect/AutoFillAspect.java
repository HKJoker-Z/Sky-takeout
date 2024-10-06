package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面 实现公共字段自动填充
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    //切入点
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill) ")
    public void autoFillPointCut() {

    }

    /**
     * 前置通知，在通知中进行赋值
     * @param joinPoint
     * @throws Throwable
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws Throwable {
        log.info("开始进行自动填充...");

        //获取当前被拦截的方法上的操作类型 like update
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType operationType = autoFill.value();//获得数据库操作类型

        //获取当前被拦截的方法的参数   实体对象
        Object[] args = joinPoint.getArgs();//获得到了所有的参数
        if (args == null || args.length == 0) {
            return;
        }

        Object entity = args[0];//获得到了实体对象

        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();//时间
        Long currentId = BaseContext.getCurrentId();//当前用户id

        //根据当前不同的操作类型，为对应的字段赋值 通过反射
        if (operationType == OperationType.INSERT) {
            //插入 四个公共字段都要被赋值
            try{
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                //通过反射进行赋值
                setCreateTime.invoke(entity, now);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
                setCreateUser.invoke(entity, currentId);
            }catch (Exception e){
                e.printStackTrace();
            }


        } else if (operationType == OperationType.UPDATE) {
            //更新 只赋值两个字段
            try{
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                //通过反射进行赋值
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
