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
package io.github.thierrysquirrel.limiter.core.factory.execution;

import io.github.thierrysquirrel.limiter.annotation.LimitedService;
import io.github.thierrysquirrel.limiter.core.constant.ServiceStatusConstant;
import io.github.thierrysquirrel.limiter.core.error.LimitException;
import io.github.thierrysquirrel.limiter.core.factory.LimitedServiceStrategyFactory;
import io.github.thierrysquirrel.limiter.core.factory.ServiceDomainFactory;
import io.github.thierrysquirrel.limiter.strategy.LimitedServiceStrategy;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * ClassName: LimitedServiceFactoryExecution
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class LimitedServiceFactoryExecution {
    private LimitedServiceFactoryExecution() {
    }

    public static Object execution(Map<Class<?>, Object> registrationMap, Callable<?> point, LimitedService limitedService, String methodString, Class<?>[] parameterTypes, Object[] parameter) throws LimitException {
        ServiceStatusConstant serviceStatusConstant = ServiceDomainFactory.getServiceStatusConstant(methodString);
        LimitedServiceStrategy limitedServiceStrategy = LimitedServiceStrategyFactory.getLimitedServiceStrategy(serviceStatusConstant);
        return limitedServiceStrategy.limitedService(registrationMap, point, limitedService, methodString, parameterTypes, parameter);
    }
}
