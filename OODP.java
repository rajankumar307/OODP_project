/*Better to use Djikstra algorithm to find the shortest distance between two node.


// CPP program to find the shortest pairwise 
// distance between any two different good nodes. 
#include <bits/stdc++.h> 
using namespace std; 

#define N 100005 
const int MAXI = 99999999; 

// Function to add edges 
void add_edge(vector<pair<int, int> > gr[], int x, 
			int y, int weight) 
{ 
	gr[x].push_back({ y, weight }); 
	gr[y].push_back({ x, weight }); 
} 

// Function to find the shortest 
// distance between any pair of 
// two different good nodes 
int minDistance(vector<pair<int, int> > gr[], int n, 
				int dist[], int vis[], int a[], int k) 
{ 
	// Keeps minimum element on top 
	priority_queue<pair<int, int>, vector<pair<int, int> >, 
				greater<pair<int, int> > > q; 

	// To keep required answer 
	int ans = MAXI; 

	for (int i = 1; i <= n; i++) { 
		// If it is not good vertex 
		if (!a[i]) 
			continue; 

		// Keep all vertices not visited 
		// and distance as MAXI 
		for (int j = 1; j <= n; j++) { 
			dist[j] = MAXI; 
			vis[j] = 0; 
		} 

		// Distance from ith vertex to ith is zero 
		dist[i] = 0; 

		// Make queue empty 
		while (!q.empty()) 
			q.pop(); 

		// Push the ith vertex 
		q.push({ 0, i }); 

		// Count the good vertices 
		int good = 0; 

		while (!q.empty()) { 
			// Take the top element 
			int v = q.top().second; 

			// Remove it 
			q.pop(); 

			// If it is already visited 
			if (vis[v]) 
				continue; 
			vis[v] = 1; 

			// Count good vertices 
			good += a[v]; 

			// If distance from vth vertex 
			// is greater than ans 
			if (dist[v] > ans) 
				break; 

			// If two good vertices are found 
			if (good == 2 and a[v]) { 
				ans = min(ans, dist[v]); 
				break; 
			} 

			// Go to all adjacent vertices 
			for (int j = 0; j < gr[v].size(); j++) { 
				int to = gr[v][j].first; 
				int weight = gr[v][j].second; 

				// if distance is less 
				if (dist[v] + weight < dist[to]) { 
					dist[to] = dist[v] + weight; 
					q.push({ dist[to], to }); 
				} 
			} 
		} 
	} 

	// Return the required answer 
	return ans; 
} 

// Driver code 
int main() 
{ 
	// Number of vertices and edges 
	int n = 5, m = 5; 

	vector<pair<int, int> > gr[N]; 

	// Function call to add edges 
	add_edge(gr, 1, 2, 3); 
	add_edge(gr, 1, 2, 3); 
	add_edge(gr, 2, 3, 4); 
	add_edge(gr, 3, 4, 1); 
	add_edge(gr, 4, 5, 8); 

	// Number of good nodes 
	int k = 3; 

	int a[N], vis[N], dist[N]; 

	// To keep good vertices 
	a[1] = a[3] = a[5] = 1; 

	cout << minDistance(gr, n, dist, vis, a, k); 

	return 0; 
} 
