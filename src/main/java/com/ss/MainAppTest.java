package com.ss;

import com.ss.tests.MemoTest;
import junit.framework.Test;
import junit.framework.TestResult;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitResultFormatter;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;
import org.apache.tools.ant.taskdefs.optional.junit.XMLJUnitResultFormatter;
import org.junit.internal.TextListener;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runners.Suite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainAppTest {

    @RunWith(Suite.class)
    @Suite.SuiteClasses({
            MemoTest.class
    })
    public static class AllTests {

    }
    public static void main(String[] args) {
        System.out.println("Running tests!!!");

        if (args.length <= 0) {
            throw new RuntimeException("Reports dir must be on arg[0]");
        }

        final File reportDir = new File(args[0]);
        reportDir.deleteOnExit();
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        reportDir.mkdirs();
        junit.addListener(new JUnitResultFormatterAsRunListener(new XMLJUnitResultFormatter()) {
            @Override
            public void testStarted(Description description) throws Exception {
                formatter.setOutput(new FileOutputStream(new File(reportDir, "TEST-" + description.getMethodName()
                        + ".xml")));
                super.testStarted(description);
            }
        });
        junit.run(new Computer(), AllTests.class).getFailureCount();
    }

    public static class JUnitResultFormatterAsRunListener extends RunListener {
        protected final JUnitResultFormatter formatter;
        private ByteArrayOutputStream stdout,stderr;
        private PrintStream oldStdout,oldStderr;
        private long problem;
        private long startTime;

        private JUnitResultFormatterAsRunListener(JUnitResultFormatter formatter) {
            this.formatter = formatter;
        }

        @Override
        public void testRunStarted(Description description) throws Exception {
        }

        @Override
        public void testRunFinished(Result result) throws Exception {

        }

        @Override
        public void testStarted(Description description) throws Exception {
            formatter.startTestSuite(new JUnitTest(description.getDisplayName()));
            formatter.startTest(new DescriptionAsTest(description));
            problem = 0;
            startTime = System.currentTimeMillis();

            this.oldStdout = System.out;
            this.oldStderr = System.err;
            System.setOut(new PrintStream(stdout = new ByteArrayOutputStream()));
            System.setErr(new PrintStream(stderr = new ByteArrayOutputStream()));
        }

        @Override
        public void testFinished(Description description) throws Exception {
            System.out.flush();
            System.err.flush();
            System.setOut(oldStdout);
            System.setErr(oldStderr);

            formatter.setSystemOutput(stdout.toString());
            formatter.setSystemError(stderr.toString());
            formatter.endTest(new DescriptionAsTest(description));

            JUnitTest suite = new JUnitTest(description.getDisplayName());
            suite.setCounts(1,problem,0);
            suite.setRunTime(System.currentTimeMillis()-startTime);
            formatter.endTestSuite(suite);
        }

        @Override
        public void testFailure(Failure failure) throws Exception {
            testAssumptionFailure(failure);
        }

        @Override
        public void testAssumptionFailure(Failure failure) {
            problem++;
            formatter.addError(new DescriptionAsTest(failure.getDescription()), failure.getException());
        }

        @Override
        public void testIgnored(Description description) throws Exception {
            super.testIgnored(description);
        }
    }

    public static class DescriptionAsTest implements Test {
        private final Description description;

        public DescriptionAsTest(Description description) {
            this.description = description;
        }

        public int countTestCases() {
            return 1;
        }

        public void run(TestResult result) {
            throw new UnsupportedOperationException();
        }

        public String getName() {
            return description.getDisplayName();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DescriptionAsTest that = (DescriptionAsTest) o;

            return description.equals(that.description);

        }

        @Override
        public int hashCode() {
            return description.hashCode();
        }
    }
}
