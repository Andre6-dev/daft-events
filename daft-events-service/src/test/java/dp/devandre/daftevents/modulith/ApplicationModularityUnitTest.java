package dp.devandre.daftevents.modulith;

import dp.devandre.daftevents.DaftEventsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ApplicationModularityUnitTest {

    ApplicationModules modules = ApplicationModules.of(DaftEventsApplication.class);

    @Test
    void verifiesModularStructure() {
        modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }

    @Test
    void createApplicationModuleModel() {
        modules.forEach(module -> System.out.println(module.getName()));
    }
}
