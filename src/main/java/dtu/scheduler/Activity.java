// Author: Kristian Sofus Knudsen
//
// Simple implementation to pass the tests in which it is involved

package dtu.scheduler;

public class Activity {

	private double hoursSpent;
	
	public Activity() {
		hoursSpent = 0;
	}

	public void registerHours(double hours) {
		hoursSpent += hours;
	}

	public double getHoursSpent() {
		return hoursSpent;
	}
}

io.cucumber.core.gherkin.FeatureParserException: Failed to parse resource at: file:///home/mads10m/google-drive/uni/eclipse-workspace/SoftEng-projekt/use_cases/login.feature
(1:1): expected: #EOF, #Language, #TagLine, #FeatureLine, #Comment, #Empty, got '//Philip Hviid'
	at io.cucumber.core.gherkin.messages.GherkinMessagesFeatureParser.parse(GherkinMessagesFeatureParser.java:52)
	at io.cucumber.core.feature.FeatureParser.parseResource(FeatureParser.java:43)
	at java.base/java.util.function.BiFunction.lambda$andThen$0(BiFunction.java:70)
	at io.cucumber.core.resource.ResourceScanner.lambda$processResource$1(ResourceScanner.java:79)
	at io.cucumber.core.resource.PathScanner$ResourceFileVisitor.visitFile(PathScanner.java:70)
	at io.cucumber.core.resource.PathScanner$ResourceFileVisitor.visitFile(PathScanner.java:55)
	at java.base/java.nio.file.Files.walkFileTree(Files.java:2725)
	at io.cucumber.core.resource.PathScanner.findResourcesForPath(PathScanner.java:48)
	at io.cucumber.core.resource.PathScanner.findResourcesForUri(PathScanner.java:28)
	at io.cucumber.core.resource.ResourceScanner.findResourcesForUri(ResourceScanner.java:61)
	at io.cucumber.core.resource.ResourceScanner.scanForResourcesUri(ResourceScanner.java:134)
	at io.cucumber.core.runtime.FeaturePathFeatureSupplier.loadFeatures(FeaturePathFeatureSupplier.java:62)
	at io.cucumber.core.runtime.FeaturePathFeatureSupplier.get(FeaturePathFeatureSupplier.java:45)
	at io.cucumber.junit.Cucumber.<init>(Cucumber.java:158)
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
	at org.junit.internal.builders.AnnotatedBuilder.buildRunner(AnnotatedBuilder.java:104)
	at org.junit.vintage.engine.discovery.DefensiveAllDefaultPossibilitiesBuilder$DefensiveAnnotatedBuilder.buildRunner(DefensiveAllDefaultPossibilitiesBuilder.java:114)
	at org.junit.internal.builders.AnnotatedBuilder.runnerForClass(AnnotatedBuilder.java:86)
	at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:70)
	at org.junit.internal.builders.AllDefaultPossibilitiesBuilder.runnerForClass(AllDefaultPossibilitiesBuilder.java:37)
	at org.junit.vintage.engine.discovery.DefensiveAllDefaultPossibilitiesBuilder.runnerForClass(DefensiveAllDefaultPossibilitiesBuilder.java:57)
	at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:70)
	at org.junit.vintage.engine.discovery.ClassSelectorResolver.resolveTestClass(ClassSelectorResolver.java:66)
	at org.junit.vintage.engine.discovery.ClassSelectorResolver.resolve(ClassSelectorResolver.java:47)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.lambda$resolve$2(EngineDiscoveryRequestResolution.java:134)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
	at java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1632)
	at java.base/java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:127)
	at java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:502)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:488)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:543)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.resolve(EngineDiscoveryRequestResolution.java:185)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.resolve(EngineDiscoveryRequestResolution.java:125)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.resolveCompletely(EngineDiscoveryRequestResolution.java:91)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.run(EngineDiscoveryRequestResolution.java:82)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolver.resolve(EngineDiscoveryRequestResolver.java:113)
	at org.junit.vintage.engine.discovery.VintageDiscoverer.discover(VintageDiscoverer.java:44)
	at org.junit.vintage.engine.VintageTestEngine.discover(VintageTestEngine.java:63)
	at org.junit.platform.launcher.core.EngineDiscoveryOrchestrator.discoverEngineRoot(EngineDiscoveryOrchestrator.java:103)
	at org.junit.platform.launcher.core.EngineDiscoveryOrchestrator.discover(EngineDiscoveryOrchestrator.java:85)
	at org.junit.platform.launcher.core.DefaultLauncher.discover(DefaultLauncher.java:92)
	at org.junit.platform.launcher.core.DefaultLauncher.discover(DefaultLauncher.java:67)
	at org.eclipse.jdt.internal.junit5.runner.JUnit5TestReference.<init>(JUnit5TestReference.java:46)
	at org.eclipse.jdt.internal.junit5.runner.JUnit5TestLoader.createUnfilteredTest(JUnit5TestLoader.java:76)
	at org.eclipse.jdt.internal.junit5.runner.JUnit5TestLoader.createTest(JUnit5TestLoader.java:66)
	at org.eclipse.jdt.internal.junit5.runner.JUnit5TestLoader.loadTests(JUnit5TestLoader.java:53)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:525)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:768)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:464)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:210)

