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
package io.github.thierrysquirrel.limiter.strategy;

import io.github.thierrysquirrel.limiter.annotation.LimitedService;
import io.github.thierrysquirrel.limiter.core.error.LimitException;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * ClassName: LimitedServiceStrategy
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
@FunctionalInterface
public interface LimitedServiceStrategy {
    /**
     * limitedService
     *
     * @param registrationMap registrationMap
     * @param point           point
     * @param limitedService  limitedService
     * @param methodString    methodString
     * @param parameterTypes  parameterTypes
     * @param parameter       parameter
     * @return Object
     * @throws LimitException limitException
     */
    Object limitedService(Map<Class<?>, Object> registrationMap, Callable<?> point, LimitedService limitedService, String methodString, Class<?>[] parameterTypes, Object[] parameter) throws LimitException;
}
