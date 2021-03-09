package io.jenkins.plugins.calculator;

import io.jenkins.plugins.calculator.Calculator;
import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import hudson.model.Label;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class HelloWorldBuilderTest {

    @Rule
    public JenkinsRule jenkins = new JenkinsRule();

    final Integer firstNumber = 1;
    final Integer secondNumber = 2;
    final String Calculator="add";

    @Ignore
    @Test
    public void testConfigRoundtrip() throws Exception {
        FreeStyleProject project = jenkins.createFreeStyleProject();
        project.getBuildersList().add(new Calculator(firstNumber,secondNumber,Calculator));
        project = jenkins.configRoundtrip(project);
        jenkins.assertEqualDataBoundBeans(new Calculator(firstNumber,secondNumber,Calculator), project.getBuildersList().get(0));
    }


}