/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.plugin.wlm.action;

import org.opensearch.cluster.metadata.WorkloadGroup;
import org.opensearch.core.action.ActionResponse;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
import org.opensearch.core.rest.RestStatus;
import org.opensearch.core.xcontent.ToXContent;
import org.opensearch.core.xcontent.ToXContentObject;
import org.opensearch.core.xcontent.XContentBuilder;

import java.io.IOException;

/**
 * Response for the create API for WorkloadGroup
 *
 * @opensearch.experimental
 */
public class CreateWorkloadGroupResponse extends ActionResponse implements ToXContent, ToXContentObject {
    private final WorkloadGroup workloadGroup;
    private final RestStatus restStatus;

    /**
     * Constructor for CreateWorkloadGroupResponse
     * @param workloadGroup - The WorkloadGroup to be included in the response
     * @param restStatus - The restStatus for the response
     */
    public CreateWorkloadGroupResponse(final WorkloadGroup workloadGroup, RestStatus restStatus) {
        this.workloadGroup = workloadGroup;
        this.restStatus = restStatus;
    }

    /**
     * Constructor for CreateWorkloadGroupResponse
     * @param in - A {@link StreamInput} object
     */
    public CreateWorkloadGroupResponse(StreamInput in) throws IOException {
        workloadGroup = new WorkloadGroup(in);
        restStatus = RestStatus.readFrom(in);
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        workloadGroup.writeTo(out);
        RestStatus.writeTo(out, restStatus);
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        return workloadGroup.toXContent(builder, params);
    }

    /**
     * workloadGroup getter
     */
    public WorkloadGroup getWorkloadGroup() {
        return workloadGroup;
    }

    /**
     * restStatus getter
     */
    public RestStatus getRestStatus() {
        return restStatus;
    }
}
