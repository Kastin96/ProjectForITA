package com.example.aspects;

import com.example.database.EntityManagerHelper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class JpaTransactionAspect {
    private final EntityManagerHelper helper;

    @SneakyThrows
    @Around("@annotation(JpaTransaction)")
    public Object transaction(ProceedingJoinPoint joinPoint) {
        EntityManager entityManager = helper.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        if (!transaction.isActive()){
            transaction.begin();
        }
        log.info("The transaction has been created for: {}", joinPoint.getSignature());

        Object result = joinPoint.proceed();

        transaction.commit();
        log.info("The transaction is closed for: {}", joinPoint.getSignature());
        return result;
    }

}
