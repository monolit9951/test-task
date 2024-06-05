package com.IntegrityCheckTeam.JavaTechnicalChallenge.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.IntegrityCheckTeam.JavaTechnicalChallenge")
public class ItemRepositoryArchTests {
    private static final String REPOSITORY_PACKAGE_PATH = "com.IntegrityCheckTeam.JavaTechnicalChallenge.repository";

    @ArchTest
    static final ArchRule repositoriesShouldBeAnnotatedWithRepository = classes()
            .that()
            .resideInAnyPackage(REPOSITORY_PACKAGE_PATH)
            .should().beAnnotatedWith(Repository.class)
            .because("Repository interfaces should be annotated with repository");

    @ArchTest
    static final ArchRule repositoriesShouldBeInterfaces = classes()
            .that()
            .resideInAnyPackage(REPOSITORY_PACKAGE_PATH)
            .should().beInterfaces()
            .because("Repositories should be interfaces");

    @ArchTest
    static final ArchRule repositoriesShouldHavePostfixRepository = classes()
            .that()
            .resideInAnyPackage(REPOSITORY_PACKAGE_PATH)
            .should().haveSimpleNameEndingWith("Repository")
            .because("Repositories should be end with Repository");
}
