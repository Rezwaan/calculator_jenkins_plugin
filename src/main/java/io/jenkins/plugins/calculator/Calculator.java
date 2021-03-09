package io.jenkins.plugins.calculator;

import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;

public class Calculator extends Builder implements SimpleBuildStep {

    private final Integer firstNumber;
    private final Integer secondNumber;
    private final String Calculator;

    @DataBoundConstructor
    public Calculator(Integer firstNumber, Integer secondNumber, String Calculator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.Calculator = Calculator;

    }

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public String getCalculator() {
        return Calculator;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        if ("add".equals(Calculator)) {
            Integer addition = firstNumber + secondNumber;
            listener.getLogger().println("Addition of two number is :" + addition);
        } else if ("sub".equals(Calculator)) {
            Integer subtraction = firstNumber - secondNumber;
            listener.getLogger().println("Subtraction of two number is :" + subtraction);
        } else if ("mul".equals(Calculator)) {
            Integer multiply = firstNumber * secondNumber;
            listener.getLogger().println("Multiplication of two number is :" + multiply);
        } else if ("div".equals(Calculator)) {
            Integer divide = firstNumber / secondNumber;
            listener.getLogger().println("Division of two number is :" + divide);
        }

    }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckFirstNumber(@QueryParameter Integer firstNumber)
                throws IOException, ServletException {
            if (firstNumber == null) {
                return FormValidation.error("Please enter any number");
            }
            if (firstNumber <= 0) {
                return FormValidation.error("Please enter valid number greater then 0");
            }
            if (firstNumber > 10000) {
                return FormValidation.error("Please enter number less than 10000");
            }
            return FormValidation.ok();
        }

        public FormValidation doCheckSecondNumber(@QueryParameter Integer secondNumber)
                throws IOException, ServletException {
            if (secondNumber == null) {
                return FormValidation.error("Please enter any number");
            }
            if (secondNumber <= 0) {
                return FormValidation.error("Please enter valid number greater then 0");
            }
            if (secondNumber > 10000) {
                return FormValidation.error("Please enter number less than 10000");
            }
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return Messages.HelloWorldBuilder_DescriptorImpl_DisplayName();
        }

    }

}
