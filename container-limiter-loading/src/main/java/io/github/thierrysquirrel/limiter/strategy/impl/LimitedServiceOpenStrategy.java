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
package io.github.thierrysquirrel.limiter.strategy.impl;

import io.github.thierrysquirrel.limiter.annotation.LimitedService;
import io.github.thierrysquirrel.limiter.core.error.LimitException;
import io.github.thierrysquirrel.limiter.core.factory.LimitedServiceFactory;
import io.github.thierrysquirrel.limiter.core.factory.ServiceDomainFactory;
import io.github.thierrysquirrel.limiter.strategy.LimitedServiceStrategy;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: LimitedServiceOpenStrategy
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class LimitedServiceOpenStrategy implements LimitedServiceStrategy {

    private static final Logger logger = Logger.getLogger(LimitedServiceOpenStrategy.class.getName());

    @Override
    public Object limitedService(Map<Class<?>, Object> registrationMap, Callable<?> point, LimitedService limitedService, String methodString, Class<?>[] parameterTypes, Object[] parameter) throws LimitException {
        try {
            long beforeTime = System.currentTimeMillis();
            Object proceed = point.call();
            ServiceDomainFactory.successExecution(methodString, beforeTime);
            return proceed;
        } catch (Throwable throwable) {
            String logMsg = "LimitedServiceOpenStrategy Error";
            logger.log(Level.WARNING, logMsg, throwable);

            ServiceDomainFactory.fail(methodString);
            return LimitedServiceFactory.fallback(registrationMap, limitedService, parameterTypes, parameter);
        }
    }
}
