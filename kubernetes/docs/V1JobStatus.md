

# V1JobStatus

JobStatus represents the current state of a Job.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**active** | **Integer** | The number of actively running pods. |  [optional]
**completionTime** | [**OffsetDateTime**](OffsetDateTime.md) | Represents time when the job was completed. It is not guaranteed to be set in happens-before order across separate operations. It is represented in RFC3339 form and is in UTC. The completion time is only set when the job finishes successfully. |  [optional]
**conditions** | [**List&lt;V1JobCondition&gt;**](V1JobCondition.md) | The latest available observations of an object&#39;s current state. When a job fails, one of the conditions will have type &#x3D;&#x3D; \&quot;Failed\&quot;. More info: https://kubernetes.io/docs/concepts/workloads/controllers/jobs-run-to-completion/ |  [optional]
**failed** | **Integer** | The number of pods which reached phase Failed. |  [optional]
**startTime** | [**OffsetDateTime**](OffsetDateTime.md) | Represents time when the job was acknowledged by the job controller. It is not guaranteed to be set in happens-before order across separate operations. It is represented in RFC3339 form and is in UTC. |  [optional]
**succeeded** | **Integer** | The number of pods which reached phase Succeeded. |  [optional]



