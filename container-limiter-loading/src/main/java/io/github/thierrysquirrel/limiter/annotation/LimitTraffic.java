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

package io.github.thierrysquirrel.limiter.annotation;


import java.lang.annotation.*;

/**
 * ClassName: LimitTraffic
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimitTraffic {

    /**
     * Limit Name,If The Names Are The Same, Use The Same Token Bucket
     * <p>
     * 限流名称,如果名称一样,使用同一个令牌桶
     *
     * @return String
     */
    String limitName();

    /**
     * Permits Per Second
     * <p>
     * 每秒许可数
     *
     * @return double
     */

    double permitsPerSecond();

    /**
     * Fallback Class
     * <p>
     * 回退Class
     *
     * @return Class
     */
    Class<?> fallbackClass();

    /**
     * Fallback Method
     * <p>
     * 回退方法
     *
     * @return String
     */

    String fallbackMethod();
}
