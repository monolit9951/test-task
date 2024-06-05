package com.IntegrityCheckTeam.JavaTechnicalChallenge.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.IntegrityCheckTeam.JavaTechnicalChallenge")
public class ItemDocumentArchTests {
    private static final String DOCUMENT_PACKAGE_PATH = "com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.document";

    @ArchTest
    static final ArchRule documentsShouldBeAnnotatedWithDocument = classes()
            .that()
            .resideInAnyPackage(DOCUMENT_PACKAGE_PATH)
            .should().beAnnotatedWith(Document.class)
            .because("Document classes should be annotated with document");

    @ArchTest
    static final ArchRule documentsShouldBeClasses = classes()
            .that()
            .resideInAnyPackage(DOCUMENT_PACKAGE_PATH)
            .should().beTopLevelClasses()
            .because("Documents should be classes");

    @ArchTest
    static final ArchRule documentsShouldHavePostfixDocument = classes()
            .that()
            .resideInAnyPackage(DOCUMENT_PACKAGE_PATH)
            .should().haveSimpleNameEndingWith("Document")
            .because("Documents should be end with Document");
}
