```java
D[i][j] = 정점 i에서 정점 j 로의 최소 비용
AllPairShortest(D[][])
	for k in 1 -> n
		for i in 1 -> n
			for j in 1 -> n
				D[i][j] <- min(D[i][k] + D[k][j] , D[i][j])
```
