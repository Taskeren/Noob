package cn.taskeren.noob.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Random;

// from https://gist.github.com/nitu2003/1db8fd362c81dcda92c985dd5ce4d5e8
@SuppressWarnings("WeakerAccess")
public class WeightedRandom<V> {

	private final Random rand;

	/** 元素池，随机出来指针后从这里提取元素 */
	private final List<V> pool = Lists.newArrayList();

	/** 加权最大值 */
	private int cap = 0;
	/** 加权元素池（元素指针，元素加权值） */
	private final Map<Integer, Integer> caps = Maps.newHashMap();

	public WeightedRandom() {
		this(new Random());
	}

	public WeightedRandom(Random rand) {
		this.rand = rand;
	}

	public void add(int weight, V element) {
		pool.add(element); // 放到元素池里
		caps.put(pool.size()-1, cap+weight); // 放到加权元素池里
		cap += weight; // 更新最大加权值
	}

	public V next() {
		final int rind = rand.nextInt(cap); // 随机一个加权值
		for(Map.Entry<Integer, Integer> entry : caps.entrySet()) { // 找到刚好比随机加权值小的元素指针
			final int ind = entry.getKey();
			final int cap = entry.getValue();
			if(cap > rind) {
				return pool.get(ind);
			}
		}
		throw new IllegalStateException("unexpected");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("WeightRandom{ ");
		caps.forEach( (index, cap) -> {
			final V content = pool.get(index);
			sb.append(content).append("=").append(cap).append(" ");
		});
		sb.append("}");
		return sb.toString();
	}

}
