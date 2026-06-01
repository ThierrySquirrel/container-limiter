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

package io.github.thierrysquirrel.limiter.registration;

import io.github.thierrysquirrel.limiter.annotation.Limit;
import io.github.thierrysquirrel.limiter.core.constant.ServiceStatusConstant;
import io.github.thierrysquirrel.limiter.core.factory.LimitedServiceStrategyFactory;
import io.github.thierrysquirrel.limiter.proxy.utils.LimiterProxyUtils;
import io.github.thierrysquirrel.limiter.strategy.impl.LimitedServiceCloseStrategy;
import io.github.thierrysquirrel.limiter.strategy.impl.LimitedServiceOpenStrategy;
import io.github.thierrysquirrel.limiter.strategy.impl.LimitedServiceTryStrategy;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName: LimiterRegistration
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class LimiterRegistration {
    private LimiterRegistration() {
    }

    public static void limiterRegistrationScannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        LimitedServiceStrategyFactory.putLimitedServiceStrategy(ServiceStatusConstant.CLOSE, new LimitedServiceCloseStrategy());
        LimitedServiceStrategyFactory.putLimitedServiceStrategy(ServiceStatusConstant.OPEN, new LimitedServiceOpenStrategy());
        LimitedServiceStrategyFactory.putLimitedServiceStrategy(ServiceStatusConstant.TRY, new LimitedServiceTryStrategy());

        for (Class<?> thisClass : scannerClassList) {
            Limit limit = thisClass.getAnnotation(Limit.class);
            if (Objects.isNull(limit)) {
                continue;
            }
            Object otterProxy = LimiterProxyUtils.createLimiterProxy(thisClass, registrationMap);
            registrationMap.put(thisClass, otterProxy);
        }

    }
}
