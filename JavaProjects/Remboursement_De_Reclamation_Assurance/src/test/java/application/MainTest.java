package application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private String nomFichierStats = "statistiquesTest.json";

    @Test
    void verifierValeurArgs() {
        String[] arguments = {"arg1", "fichierSortieTest.json"};
        assertDoesNotThrow(() -> Main.verifierValeurArgs(arguments, nomFichierStats));
    }

    @Test
    public void verifierArgumentsAvecUnArgument() {
        String[] arguments = {"arg1"};
        assertDoesNotThrow(() -> Main.verifierValeurArgs(arguments,  nomFichierStats));
    }

    @Test
    public void verifierArgumentsAvecTroisArguments() {
        String[] arguments = {"arg1", "fichierSortieTest.json", "arg3"};
        assertDoesNotThrow(() -> Main.verifierValeurArgs(arguments,nomFichierStats));
    }

    @Test
    void validerArguments() {
        String[] validArgs1 = {"arg1", "fichierSortieTest.json"};
        Assertions.assertDoesNotThrow(() -> Main.validerArguments(validArgs1, nomFichierStats));

        String[] validArgs2 = {"arg1"};
        Assertions.assertDoesNotThrow(() -> Main.validerArguments(validArgs2, nomFichierStats));
        String[] validArgs = {};
        Assertions.assertDoesNotThrow(() -> Main.validerArguments(validArgs, nomFichierStats));

    }
}