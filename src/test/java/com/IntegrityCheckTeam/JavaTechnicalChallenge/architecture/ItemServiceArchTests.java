package com.IntegrityCheckTeam.JavaTechnicalChallenge.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.IntegrityCheckTeam.JavaTechnicalChallenge")
public class ItemServiceArchTests {
    private static final String SERVICE_PACKAGE_PATH = "com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.impl";
    @ArchTest
    static final ArchRule serviceImplsShouldBeAnnotatedWithService = classes()
            .that()
            .resideInAnyPackage(SERVICE_PACKAGE_PATH)
            .should().beAnnotatedWith(Service.class)
            .because("Services should be annotated with service");

    @ArchTest
    static final ArchRule serviceImplsShouldBeClasses = classes()
            .that()
            .resideInAnyPackage(SERVICE_PACKAGE_PATH)
            .should().beTopLevelClasses()
            .because("Service impls should be classes");

    @ArchTest
    static final ArchRule serviceImplsShouldHavePostfixServiceImpl = classes()
            .that()
            .resideInAnyPackage(SERVICE_PACKAGE_PATH)
            .should().haveSimpleNameEndingWith("ServiceImpl")
            .because("Service impls should be end with ServiceImpl");
}
