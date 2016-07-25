/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.platform.engine.support.descriptor;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

import java.util.Objects;
import java.util.Optional;

import org.junit.platform.commons.meta.API;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.commons.util.ToStringBuilder;

/**
 * Java class based {@link org.junit.platform.engine.TestSource} with an optional
 * {@linkplain FilePosition position}.
 *
 * @since 1.0
 */
@API(Experimental)
public class JavaClassSource implements JavaSource {

	private static final long serialVersionUID = 1L;

	private final Class<?> javaClass;

	private final FilePosition filePosition;

	/**
	 * Create a new {@code JavaClassSource} using the supplied
	 * {@link Class javaClass}.
	 *
	 * @param javaClass the Java class; must not be {@code null}
	 */
	public JavaClassSource(Class<?> javaClass) {
		this(javaClass, null);
	}

	/**
	 * Create a new {@code JavaClassSource} using the supplied
	 * {@link Class javaClass} and {@link FilePosition filePosition}.
	 *
	 * @param javaClass the Java class; must not be {@code null}
	 * @param filePosition the position in the Java source file; may be {@code null}
	 */
	public JavaClassSource(Class<?> javaClass, FilePosition filePosition) {
		this.javaClass = Preconditions.notNull(javaClass, "class must not be null");
		this.filePosition = filePosition;
	}

	/**
	 * Get the {@linkplain Class Java class} of this source.
	 *
	 * @see #getPosition()
	 */
	public final Class<?> getJavaClass() {
		return this.javaClass;
	}

	/**
	 * Get the {@linkplain FilePosition position} in the Java source file for
	 * the associated {@linkplain #getJavaClass Java class}, if available.
	 *
	 * @see #getJavaClass()
	 */
	public final Optional<FilePosition> getPosition() {
		return Optional.ofNullable(this.filePosition);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		JavaClassSource that = (JavaClassSource) o;
		return Objects.equals(this.javaClass, that.javaClass) && Objects.equals(this.filePosition, that.filePosition);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.javaClass, this.filePosition);
	}

	@Override
	public String toString() {
		// @formatter:off
		return new ToStringBuilder(this)
				.append("javaClass", this.javaClass.getName())
				.append("filePosition", this.filePosition)
				.toString();
		// @formatter:on
	}

}
