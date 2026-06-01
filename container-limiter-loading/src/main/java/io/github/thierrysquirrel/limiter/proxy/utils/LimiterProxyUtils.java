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
package io.github.thierrysquirrel.limiter.proxy.utils;

import io.github.thierrysquirrel.limiter.proxy.LimiterProxy;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: LimiterProxyUtils
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class LimiterProxyUtils {

    private static final Logger logger = Logger.getLogger(LimiterProxyUtils.class.getName());

    private LimiterProxyUtils() {
    }

    public static Object createLimiterProxy(Class<?> limiterClass, Map<Class<?>, Object> registrationMap) {
        Class<?> otterProxyClass = new ByteBuddy()
                .subclass(limiterClass)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(new LimiterProxy(registrationMap)))
                .make()
                .load(limiterClass.getClassLoader())
                .getLoaded();
        return newInstance(otterProxyClass);
    }

    private static Object newInstance(Class<?> otterClass) {
        Object proxyInstance = null;
        try {

            proxyInstance = otterClass.getDeclaredConstructor().newInstance();
        } catch (Throwable e) {
            String logMsg = "newInstance Error";
            logger.log(Level.WARNING, logMsg, e);
        }
        return proxyInstance;
    }
}
