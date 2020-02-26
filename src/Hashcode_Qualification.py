from pprint import pprint
import sys

input_file = "a_example.txt"
# input_file = "b_read_on.txt"
output_file = "a_output.txt"

with open(input_file, 'r') as f:
    input_data = f.read()
    input_data = input_data.splitlines()
    pprint(input_data)
    B, L, D = input_data[0].split(' ')
    B, L, D = int(B), int(L), int(D)

    S = input_data[1].split(' ')  # Score of books
    S = [int(i) for i in S]

    libraries = []
    for i in range(2, (2 * L) + 1, 2):
        print(i)
        N_j, T_j, M_j = input_data[i].split(' ')
        N_j, T_j, M_j = int(N_j), int(T_j), int(M_j)
        B_j = input_data[i + 1].split(' ')
        B_j = set([int(x) for x in B_j])
        libraries.append((N_j, T_j, M_j, B_j))

pprint(libraries[0])

already_scanning_books = set()


# def should_include_library(library):
#     max_score_from_library = 0
#     total_days_for_max_score = 0

#     Nj = library[0]
#     Tj = library[1]
#     Mj = library[2]
#     Bj = library[3]

#     if Tj > D:  # signup takes more days than we have.
#         return False


#     for b in Bj:
#         b_score = B[b]
#         max_score_from_library += b_score

#     # max_score_from_library =
#     # inclusion_candidates.append(library)

#     pass

b_with_libs = []
print("sorting")
for i in range(0, B):
    # see which libraries the book is in...
    s = S[i]
    b = i
    libs_with_b = [libraries.index(lib) for lib in libraries if b in lib[3]]
    b_with_libs.append([b, libs_with_b])

b_with_libs = sorted(b_with_libs, key=lambda x: len(x[1]), reverse=True)
print(b_with_libs)
# b_with_libs = sorted(b_with_libs, key=lambda x: libraries[x[1]][1] - libraries[x[1]][2])
for i in range(0, len(b_with_libs)):
    b = b_with_libs[i]
    # which library to pick this book from?
    b_with_libs[i][1] = max(b[1], key=lambda x: libraries[x][1] - libraries[x][2])

b_with_libs = sorted(b_with_libs, key=lambda x: x[1], reverse=True)
print(b_with_libs)

with open(output_file, 'w') as f:
    f.write(str(L) + '\n')
    last_bb1 = None
    for bb in b_with_libs:
        if bb[1] == last_bb1:
            continue
        books_to_scan_from_library = libraries[bb[1]][3] - already_scanning_books
        already_scanning_books.update(books_to_scan_from_library)
        books_to_scan_from_library = sorted(books_to_scan_from_library, key=lambda x: S[x], reverse=True)
        print(bb, books_to_scan_from_library)
        f.write(str((bb[1])) + ' ' + str(len(books_to_scan_from_library)) + '\n')
        books_to_scan_from_library = [str(x) for x in books_to_scan_from_library]
        f.write(' '.join(books_to_scan_from_library) + '\n')
        last_bb1 = bb[1]
