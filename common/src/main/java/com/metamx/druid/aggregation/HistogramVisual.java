/*
 * Druid - a distributed column store.
 * Copyright (C) 2012  Metamarkets Group Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.metamx.druid.aggregation;

import com.google.common.base.Preconditions;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;

public class HistogramVisual
{
  @JsonProperty final public double[] breaks;
  @JsonProperty final public double[] counts;
  @JsonProperty final double min;
  @JsonProperty final double max;

  @JsonCreator
  public HistogramVisual(
      @JsonProperty double[] breaks,
      @JsonProperty double[] counts,
      @JsonProperty double min,
      @JsonProperty double max
  )
  {
    Preconditions.checkArgument(breaks != null, "breaks must not be null");
    Preconditions.checkArgument(counts != null, "counts must not be null");
    Preconditions.checkArgument(breaks.length == counts.length + 1, "breaks.length must be counts.length + 1");

    this.breaks = breaks;
    this.counts = counts;
    this.min = min;
    this.max = max;
  }

  public HistogramVisual(
        float[] breaks,
        float[] counts,
        float min,
        float max
  )
  {
    Preconditions.checkArgument(breaks != null, "breaks must not be null");
    Preconditions.checkArgument(counts != null, "counts must not be null");
    Preconditions.checkArgument(breaks.length == counts.length + 1, "breaks.length must be counts.length + 1");

    this.breaks = new double[breaks.length];
    this.counts = new double[counts.length];
    for(int i = 0; i < breaks.length; ++i) this.breaks[i] = breaks[i];
    for(int i = 0; i < counts.length; ++i) this.counts[i] = counts[i];
    this.min = min;
    this.max = max;
  }

  @Override
  public String toString()
  {
    return "HistogramVisual{" +
           "counts=" + Arrays.toString(counts) +
           ", breaks=" + Arrays.toString(breaks) +
           ", min=" + min +
           ", max=" + max +
           '}';
  }
}
