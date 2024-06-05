package com.IntegrityCheckTeam.JavaTechnicalChallenge.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.IntegrityCheckTeam.JavaTechnicalChallenge")
public class ItemExceptionsArchTests {
    private static final String EXCEPTIONS_PACKAGE_PATH = "com.IntegrityCheckTeam.JavaTechnicalChallenge.exception";

    @ArchTest
    static final ArchRule exceptionsShouldBeClasses = classes()
            .that()
            .resideInAnyPackage(EXCEPTIONS_PACKAGE_PATH)
            .should().beTopLevelClasses()
            .because("Exceptions should be classes");

    @ArchTest
    static final ArchRule exceptionsShouldHavePostfixDocument = classes()
            .that()
            .resideInAnyPackage(EXCEPTIONS_PACKAGE_PATH)
            .should().haveSimpleNameEndingWith("Exception")
            .because("Exceptions should be end with Exception");
}
