/*
 * Copyright 2013 JAXIO http://www.jaxio.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.querybyexample.jpa;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static org.querybyexample.jpa.OrderByDirection.ASC;
import static org.querybyexample.jpa.OrderByDirection.DESC;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.Attribute;

import org.hibernate.ejb.metamodel.AbstractAttribute;

/**
 * Holder class for search ordering used by the {@link SearchParameters}. When used with {@link ByNamedQueryUtil}, you pass column name. For other usage, pass the
 * property name.
 */
public class OrderBy implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Attribute<?, ?>> attributes;
    private final String property;
    private OrderByDirection direction = ASC;

    public OrderBy(OrderByDirection direction, Attribute<?, ?>... attributes) {
        this.direction = checkNotNull(direction);
        this.attributes = newArrayList(checkNotNull(attributes));
        this.property = null;
    }

    public OrderBy(OrderByDirection direction, String property) {
        this(direction, new AbstractAttribute<Object, Object>(property, null, null, null, null) {
            private static final long serialVersionUID = 1L;

            public boolean isAssociation() {
                return false;
            }

            public boolean isCollection() {
                return false;
            }
        });
    }

    public List<Attribute<?, ?>> getAttributes() {
        if (property != null) {
            ;
        }
        return attributes;
    }

    public OrderByDirection getDirection() {
        return direction;
    }

    public boolean isOrderDesc() {
        return DESC == direction;
    }
}