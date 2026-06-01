/**
 * Copyright 2026/6/2 ThierrySquirrel
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package io.github.thierrysquirrel.limiter.core.domain;

import io.github.thierrysquirrel.limiter.core.constant.ServiceStatusConstant;

import java.util.concurrent.atomic.LongAdder;

/**
 * ClassName: ServiceDomain
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class ServiceDomain {
    private ServiceStatusConstant serviceStatusConstant;
    private LongAdder successCount;
    private LongAdder failCount;
    private LongAdder timeoutCount;
    private LongAdder tryCount;
    private Long resetCountTime;
    private Long closeTime;

    public ServiceStatusConstant getServiceStatusConstant() {
        return serviceStatusConstant;
    }

    public void setServiceStatusConstant(ServiceStatusConstant serviceStatusConstant) {
        this.serviceStatusConstant = serviceStatusConstant;
    }

    public LongAdder getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(LongAdder successCount) {
        this.successCount = successCount;
    }

    public LongAdder getFailCount() {
        return failCount;
    }

    public void setFailCount(LongAdder failCount) {
        this.failCount = failCount;
    }

    public LongAdder getTimeoutCount() {
        return timeoutCount;
    }

    public void setTimeoutCount(LongAdder timeoutCount) {
        this.timeoutCount = timeoutCount;
    }

    public LongAdder getTryCount() {
        return tryCount;
    }

    public void setTryCount(LongAdder tryCount) {
        this.tryCount = tryCount;
    }

    public Long getResetCountTime() {
        return resetCountTime;
    }

    public void setResetCountTime(Long resetCountTime) {
        this.resetCountTime = resetCountTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "ServiceDomain{" +
                "serviceStatusConstant=" + serviceStatusConstant +
                ", successCount=" + successCount +
                ", failCount=" + failCount +
                ", timeoutCount=" + timeoutCount +
                ", tryCount=" + tryCount +
                ", resetCountTime=" + resetCountTime +
                ", closeTime=" + closeTime +
                '}';
    }
}
