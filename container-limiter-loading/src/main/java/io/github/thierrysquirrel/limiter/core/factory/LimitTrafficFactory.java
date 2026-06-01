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
package io.github.thierrysquirrel.limiter.core.factory;

import io.github.thierrysquirrel.limiter.annotation.LimitTraffic;
import io.github.thierrysquirrel.limiter.core.error.LimitException;
import io.github.thierrysquirrel.limiter.core.factory.execution.FallbackFactoryExecution;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * ClassName: LimitTrafficFactory
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class LimitTrafficFactory {
    private LimitTrafficFactory() {
    }

    public static Object release(Callable<?> point) throws LimitException {

        try {
            return point.call();
        } catch (Throwable throwable) {
            throw new LimitException(throwable);
        }
    }

    public static Object fallback(Map<Class<?>, Object> registrationMap, LimitTraffic limitTraffic, Class<?>[] parameterTypes, Object[] parameter) throws LimitException {
        return FallbackFactoryExecution.fallback(registrationMap, limitTraffic.fallbackClass(), limitTraffic.fallbackMethod(), parameterTypes, parameter);
    }
}
