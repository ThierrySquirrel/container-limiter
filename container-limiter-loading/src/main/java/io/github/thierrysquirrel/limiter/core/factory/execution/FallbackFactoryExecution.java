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

import io.github.thierrysquirrel.limiter.core.builder.FallbackDomainBuilder;
import io.github.thierrysquirrel.limiter.core.domain.FallbackDomain;
import io.github.thierrysquirrel.limiter.core.error.LimitException;
import io.github.thierrysquirrel.limiter.core.factory.FallbackFactory;

import java.util.Map;

/**
 * ClassName: FallbackFactoryExecution
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class FallbackFactoryExecution {
    private FallbackFactoryExecution() {
    }

    public static Object fallback(Map<Class<?>, Object> registrationMap, Class<?> fallbackClass, String fallbackMethod, Class<?>[] parameterTypes, Object[] parameter) throws LimitException {
        Object fallbackBean = registrationMap.get(fallbackClass);
        try {
            FallbackDomain fallbackDomain = FallbackDomainBuilder.builderFallbackDomain(fallbackBean, fallbackMethod, parameterTypes, parameter);
            return FallbackFactory.fallback(fallbackDomain);
        } catch (Exception e) {
            throw new LimitException(e);
        }
    }
}
