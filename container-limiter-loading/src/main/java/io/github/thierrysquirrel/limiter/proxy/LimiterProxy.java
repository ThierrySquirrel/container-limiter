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
package io.github.thierrysquirrel.limiter.proxy;

import io.github.thierrysquirrel.limiter.annotation.LimitTraffic;
import io.github.thierrysquirrel.limiter.annotation.LimitedService;
import io.github.thierrysquirrel.limiter.core.factory.execution.LimitTrafficFactoryExecution;
import io.github.thierrysquirrel.limiter.core.factory.execution.LimitedServiceFactoryExecution;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * ClassName: LimiterProxy
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class LimiterProxy {
    private Map<Class<?>, Object> registrationMap;

    public LimiterProxy(Map<Class<?>, Object> registrationMap) {
        this.registrationMap = registrationMap;
    }

    @RuntimeType
    public Object intercept(@This Object proxy, @Origin Method method, @AllArguments Object[] args, @SuperCall Callable<?> callable) throws Exception {
        LimitedService limitedService = method.getAnnotation(LimitedService.class);
        if (!Objects.isNull(limitedService)) {
            return LimitedServiceFactoryExecution.execution(registrationMap,
                    callable,
                    limitedService,
                    method.toString(),
                    method.getParameterTypes(),
                    args);
        }

        LimitTraffic limitTraffic = method.getAnnotation(LimitTraffic.class);
        if (!Objects.isNull(limitTraffic)) {
            return LimitTrafficFactoryExecution.limitTraffic(registrationMap,
                    callable,
                    limitTraffic,
                    method.getParameterTypes(),
                    args);
        }
        return callable.call();
    }

    public Map<Class<?>, Object> getRegistrationMap() {
        return registrationMap;
    }

    public void setRegistrationMap(Map<Class<?>, Object> registrationMap) {
        this.registrationMap = registrationMap;
    }
}
