import com.sun.org.apache.xalan.internal.utils.FeatureManager;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;

import cucumber.api.DataTable;
import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.RuntimeOptionsFactory;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.junit.Assertions;
import cucumber.runtime.junit.ExecutionUnitRunner;
import cucumber.runtime.junit.FeatureRunner;
import cucumber.runtime.junit.JUnitReporter;
import cucumber.runtime.model.CucumberFeature;
import cucumber.runtime.model.CucumberTagStatement;
import gherkin.formatter.model.DescribedStatement;
import gherkin.formatter.model.TagStatement;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//public class CucumberRunners extends Cucumber
//{
//
//    /**
//     * Constructor called by JUnit.
//     *
//     * @param clazz the class with the @RunWith annotation.
//     * @throws IOException                         if there is a problem
//     * @throws InitializationError if there is another problem
//     */
//    public CucumberRunners(Class clazz) throws InitializationError, IOException {
//        super(clazz);
//        int x = 2;
//    }
//
//    @Override
//    public List<FeatureRunner> getChildren() {
//        List<FeatureRunner> children = super.getChildren();
//        for(FeatureRunner featureRunner : children){
//            System.out.println(featureRunner);
//            //for(ExecutionUnitRunner executionUnitRunner : featureRunner.getDescription().){
//        }
//        return super.getChildren();
//    }
//
//    @Override
//    protected Runtime createRuntime(ResourceLoader resourceLoader, ClassLoader classLoader, RuntimeOptions runtimeOptions) throws InitializationError, IOException {
//        Runtime data = super.createRuntime(resourceLoader, classLoader, runtimeOptions);
//        return data;
//    }
//
//    private void addChildren(List<CucumberFeature> cucumberFeatures) throws InitializationError {
//        for (CucumberFeature cucumberFeature : cucumberFeatures) {
//            children.add(new FeatureRunner(cucumberFeature, runtime, jUnitReporter));
//        }
//    }
//
//}

public class MyCucumberRunner extends ParentRunner<FeatureRunner> {

    private final JUnitReporter jUnitReporter;
    private final List<FeatureRunner> children = new ArrayList<FeatureRunner>();
    private final Runtime runtime;

    /**
     * Constructor called by JUnit.
     *
     * @param clazz the class with the @RunWith annotation.
     * @throws java.io.IOException                         if there is a problem
     * @throws org.junit.runners.model.InitializationError if there is another problem
     */
    public MyCucumberRunner(Class clazz) throws InitializationError, IOException {
        super(clazz);
        ClassLoader classLoader = clazz.getClassLoader();
        Assertions.assertNoCucumberAnnotatedMethods(clazz);

        RuntimeOptionsFactory runtimeOptionsFactory = new RuntimeOptionsFactory(clazz);
        RuntimeOptions runtimeOptions = runtimeOptionsFactory.create();

        ResourceLoader resourceLoader = new MultiLoader(classLoader);
        runtime = createRuntime(resourceLoader, classLoader, runtimeOptions);

        final List<CucumberFeature> cucumberFeatures = runtimeOptions.cucumberFeatures(resourceLoader);
        jUnitReporter = new JUnitReporter(runtimeOptions.reporter(classLoader), runtimeOptions.formatter(classLoader), runtimeOptions.isStrict());
        for(CucumberFeature cucumberFeature : cucumberFeatures){
            /*for(CucumberTagStatement cucumberTagStatement : cucumberFeature.getFeatureElements()){
                cucumberTagStatement.getGherkinModel().
            }*/
        }
        cucumberFeatures.get(0).getFeatureElements();
        // create the browser config id mapping in case of multi browser add feature elements X browsers count add
        addChildren(cucumberFeatures);
    }

    /**
     * Create the Runtime. Can be overridden to customize the runtime or backend.
     *
     * @param resourceLoader used to load resources
     * @param classLoader    used to load classes
     * @param runtimeOptions configuration
     * @return a new runtime
     * @throws InitializationError if a JUnit error occurred
     * @throws IOException         if a class or resource could not be loaded
     */
    protected Runtime createRuntime(ResourceLoader resourceLoader, ClassLoader classLoader,
                                    RuntimeOptions runtimeOptions) throws InitializationError, IOException {
        ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
        return new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
    }

    @Override
    public List<FeatureRunner> getChildren() {
        return children;
    }

    @Override
    protected Description describeChild(FeatureRunner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(FeatureRunner child, RunNotifier notifier) {
        child.run(notifier);
    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
        jUnitReporter.done();
        jUnitReporter.close();
        runtime.printSummary();
    }

    private void addChildren(List<CucumberFeature> cucumberFeatures) throws InitializationError {
        for (CucumberFeature cucumberFeature : cucumberFeatures) {
            children.add(new FeatureRunner(cucumberFeature, runtime, jUnitReporter));
        }
    }

    private CucumberFeature CloneCucumberFeature(CucumberFeature cucumberFeature){
        //tagStatement.getName() - change dynamically
        //tagStatement.getId() - change dynamically
        CucumberFeature newCucumberFeature = new CucumberFeature(cucumberFeature.getGherkinFeature(), cucumberFeature.toString());
        for(CucumberTagStatement cucumberTagStatement : cucumberFeature.getFeatureElements()){
            TagStatement tagStatement = cucumberTagStatement.getGherkinModel();
            //TagStatement newTagStatement = new DescribedStatement(tagStatement.getComments(), tagStatement.getTags(), tagStatement.getKeyword(), tagStatement.getName(), tagStatement.getDescription(), tagStatement.getLine(), tagStatement.getId());
                   //CucumberTagStatement(CucumberFeature cucumberFeature, TagStatement gherkinModel)
        }
        return newCucumberFeature;
    }
}