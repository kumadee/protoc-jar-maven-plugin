/*
 * Copyright 2019 protoc-jar developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.blackrock.protocjar.maven;

import java.io.File;

import io.github.blackrock.protocjar.ProtocVersion;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.takari.maven.testing.TestResources;
import io.takari.maven.testing.executor.MavenRuntime;
import io.takari.maven.testing.executor.MavenRuntime.MavenRuntimeBuilder;
import io.takari.maven.testing.executor.MavenVersions;
import io.takari.maven.testing.executor.junit.MavenJUnitTestRunner;

@RunWith(MavenJUnitTestRunner.class)
@MavenVersions({"3.6.3", "3.8.8", "3.9.6"})
public class MojoBasicTest
{
	@Rule
	public final TestResources resources = new TestResources();
	public final MavenRuntime maven;
	
	public MojoBasicTest(MavenRuntimeBuilder mavenBuilder) throws Exception {
		this.maven = mavenBuilder.withCliOptions("-B", "-U", "-e").build();
	}

	@Test
	public void testBasic() throws Exception {
		File basedir = resources.getBasedir("basic-test");
		maven.forProject(basedir)
			.withCliOption("-Dprotobuf.version=" + ProtocVersion.PROTOC_VERSION)
			.withCliOption("-Dprotoc.version=" + ProtocVersion.PROTOC_VERSION)
			.execute("verify")
			.assertErrorFreeLog();
	}

	@Test
	public void testBasic241() throws Exception {
		File basedir = resources.getBasedir("basic-test");
		maven.forProject(basedir)
			.withCliOption("-Dprotobuf.version=2.4.1")
			.withCliOption("-Dprotoc.version=2.4.1")
			.execute("verify")
			.assertErrorFreeLog();
	}
}
