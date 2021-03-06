/*******************************************************************************
 * Copyright 2016 General Electric Company. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *******************************************************************************/

package com.ge.predix.acceptance.test.policy.evaluation;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ge.predix.test.TestConfig;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 *
 * @author 212406427
 */
@Test
@CucumberOptions
public class PolicyEvaluationCucumberTest extends AbstractTestNGCucumberTests {
    // Used as the entry point for PolicyEvaluation StepsDefinitions

    @BeforeClass
    public void setup() {
        TestConfig.setupForEclipse(); // Starts ACS when running the test in eclipse.
    }

    @Override
    public void run_cukes() throws JsonParseException, JsonMappingException, IOException {
        super.run_cukes();
    }
}
