package com.IntegrityCheckTeam.JavaTechnicalChallenge.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.IntegrityCheckTeam.JavaTechnicalChallenge")
public class ItemEnumsArchTests {
    private static final String ENUMS_PACKAGE_PATH = "com.IntegrityCheckTeam.JavaTechnicalChallenge.enums";

    @ArchTest
    static final ArchRule enumsShouldBeEnum = classes()
            .that()
            .resideInAnyPackage(ENUMS_PACKAGE_PATH)
            .should().beEnums()
            .because("Enums should be enum");

}
