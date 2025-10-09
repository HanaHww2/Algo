def solution(commands):
    answer = []
    
    parent = dict()
    values = dict()
    v_table = dict()
    
    def find(r, c):
        
        idx = (int(r), int(c))
        if idx not in parent:
            parent[idx] = idx
        if idx == parent[idx]:
            return idx
        
        parent[idx] = find(*parent[idx])
        return parent[idx]
    
    def union(r1, c1, r2, c2):
        idx1 = (int(r1), int(c1))
        idx2 = (int(r2), int(c2))
        if idx1 == idx2: return
    
        p1 = find(*idx1)
        p2 = find(*idx2)
        if p1 == p2: return
    
        if p1 in values:
            if p2 in values:
                v_table.setdefault(values[p2], set()).discard(p2)
                values.pop(p2)
            
            parent[p2] = p1
            return
        
        if p2 in values:
            parent[p1] = p2
            return
        
        parent[p2] = p1
            
    def update_val(v1, v2):
        if v1 == v2: return
        if not v2 in v_table:
            v_table[v2] = set()

        if v1 in v_table:
            for item in v_table[v1]:
                p = find(*item)
                v_table[v2].add(p)
                values[p] = v2
            v_table.pop(v1)
        return

    def update(r, c, v):
        p = find(r, c)
        
        if p in values:
            ov = values[p]
            v_table.setdefault(ov, set()).discard(p)

        values[p] = v
        if v not in v_table:
            v_table[v] = set()
        v_table[v].add(p)
        
    for c in commands:
        cl = c.split()
    
        if cl[0] == "UPDATE":
            if len(cl) == 3:
                update_val(cl[1], cl[2])
                continue
                
            update(cl[1], cl[2], cl[3])
            
        if cl[0] == "MERGE":
            union(cl[1], cl[2], cl[3], cl[4])
            
        if cl[0] == "UNMERGE":
            
            p = find(cl[1], cl[2]) 
            
            if p in values:
                v = values[p]
                values.pop(p)
                v_table.setdefault(v, set()).discard(p)
                
                values[(int(cl[1]), int(cl[2]))] = v
                v_table[v].add((int(cl[1]), int(cl[2])))
            
            members = [k for k in list(parent.keys()) if find(*k) == p]
            # 혹시 p가 parent에 없다면 보강
            if p not in members:
                members.append(p)
            for key in members:
                parent[key] = key
                if key != (int(cl[1]), int(cl[2])) and key in values:
                    v_table[values[key]].discard(key)
                    values.pop(key)
            
        if cl[0] == "PRINT":
            p = find(cl[1], cl[2])
            if p in values:
                answer.append(values[p])
            else: 
                answer.append("EMPTY")
    
    return answer