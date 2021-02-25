import glob
import ntpath
import os

files = glob.glob("*.in")


with open('run_tests.sh', 'w') as fil:
    for inp in files:
        basename = ntpath.basename(inp)
        file_name, file_ext = os.path.splitext(basename)
        fil.write(f'java -cp out/production/Competitive Main <{file_name}.in >{file_name}.out\n')
