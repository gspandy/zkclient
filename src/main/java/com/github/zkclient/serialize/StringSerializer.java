/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.zkclient.serialize;

import java.io.UnsupportedEncodingException;

import com.github.zkclient.exception.ZkMarshallingError;

/**
 * String Encoder(UTF-8 default)
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2.0
 */
public class StringSerializer implements ZkSerializer {

    private final String encode;

    public StringSerializer() {
        this("UTF-8");
    }

    public StringSerializer(String encode) {
        this.encode = encode;
    }

    @Override
    public byte[] serialize(Object data) throws ZkMarshallingError {
        try {
            return data == null ? null : ((String) data).getBytes(encode);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError(e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        try {
            return bytes == null ? (String) null : new String(bytes, encode);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError(e);
        }
    }

}
