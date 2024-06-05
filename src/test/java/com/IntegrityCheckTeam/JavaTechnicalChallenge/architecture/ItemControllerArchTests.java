package com.IntegrityCheckTeam.JavaTechnicalChallenge.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.IntegrityCheckTeam.JavaTechnicalChallenge")
public class ItemControllerArchTests {
    private static final String CONTROLLER_PACKAGE_PATH = "com.IntegrityCheckTeam.JavaTechnicalChallenge.api.controller";
    @ArchTest
    static final ArchRule controllersShouldBeAnnotatedWithRestController = classes()
            .that()
            .resideInAnyPackage(CONTROLLER_PACKAGE_PATH)
            .should().beAnnotatedWith(RestController.class)
            .because("Controllers should be annotated with RestController");

    @ArchTest
    static final ArchRule controllersShouldBeClasses = classes()
            .that()
            .resideInAnyPackage(CONTROLLER_PACKAGE_PATH)
            .should().beTopLevelClasses()
            .because("Controllers should be classes");

    @ArchTest
    static final ArchRule controllersShouldHavePostfixController = classes()
            .that()
            .resideInAnyPackage(CONTROLLER_PACKAGE_PATH)
            .should().haveSimpleNameEndingWith("Controller")
            .because("Controllers should be end with Controller");
}
