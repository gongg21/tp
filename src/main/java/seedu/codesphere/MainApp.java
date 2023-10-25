package seedu.codesphere;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.codesphere.commons.core.Config;
import seedu.codesphere.commons.core.LogsCenter;
import seedu.codesphere.commons.core.Version;
import seedu.codesphere.commons.exceptions.DataLoadingException;
import seedu.codesphere.commons.util.ConfigUtil;
import seedu.codesphere.commons.util.StringUtil;
import seedu.codesphere.logic.Logic;
import seedu.codesphere.logic.LogicManager;
import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.ReadOnlyUserPrefs;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.util.SampleDataUtil;
import seedu.codesphere.storage.CourseListStorage;
import seedu.codesphere.storage.JsonCourseListStorage;
import seedu.codesphere.storage.JsonUserPrefsStorage;
import seedu.codesphere.storage.Storage;
import seedu.codesphere.storage.StorageManager;
import seedu.codesphere.storage.UserPrefsStorage;
import seedu.codesphere.ui.Ui;
import seedu.codesphere.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing StudentList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        CourseListStorage studentListStorage = new JsonCourseListStorage(userPrefs.getStudentListFilePath());
        storage = new StorageManager(studentListStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s student list and {@code userPrefs}. <br>
     * The data from the sample student list will be used instead if {@code storage}'s student list is not found,
     * or an empty student list will be used instead if errors occur when reading {@code storage}'s student list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getCourseListFilePath());

        Optional<ReadOnlyCourseList> courseListOptional;
        ReadOnlyCourseList initialData;
        try {
            courseListOptional = storage.readCourseList();
            if (!courseListOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getCourseListFilePath()
                        + " populated with a sample StudentList.");
            }
            initialData = courseListOptional.orElseGet(SampleDataUtil::getSampleCourseList);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getCourseListFilePath() + " could not be loaded."
                    + " Will be starting with an empty StudentList.");
            initialData = new CourseList();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            if (!configOptional.isPresent()) {
                logger.info("Creating new config file " + configFilePathUsed);
            }
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataLoadingException e) {
            logger.warning("Config file at " + configFilePathUsed + " could not be loaded."
                    + " Using default config properties.");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting StudentList " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Student List ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
