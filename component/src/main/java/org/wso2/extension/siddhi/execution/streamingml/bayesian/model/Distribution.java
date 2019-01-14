/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.extension.siddhi.execution.streamingml.bayesian.model;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.wso2.siddhi.core.exception.SiddhiAppCreationException;

import java.io.Serializable;

/**
 * implements probability distributions.
 * <p>
 * generate differentiable operations to compute log probability
 * or to sample form the distribution
 */
abstract class Distribution implements Serializable {

    private static final long serialVersionUID = 7170975688226593075L;
    SameDiff sd;

    /**
     * returns the log probability p(x) given x.
     *
     * @param values x values
     * @return a differentiable SameDiff variable
     */
    public abstract SDVariable logProbability(SDVariable values);

    /**
     * returns a random sample from the distribution.
     *
     * @return a differentiable SameDiff variable
     */
    public abstract SDVariable sample();

    /**
     * returns random samples from the distribution.
     *
     * @param n number of samples
     * @return a differentiable SameDiff variable
     */
    public abstract SDVariable sample(int n);

    /**
     * returns the kl divergence w.r.t the given distribution.
     *
     * @param distribution reference distribution p(x)
     * @return kl-divergence(this, p(x))
     */
    public abstract SDVariable klDivergence(Distribution distribution) throws SiddhiAppCreationException;
}

