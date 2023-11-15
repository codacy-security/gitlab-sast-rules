#!/usr/bin/env ruby

# TestCasePrecenceCheck ensures that a test case file with expected name and
# file extension exists for all rules.
module TestCasePresenceCheck
  def self.run
    ok = true
    Dir.entries('./').select { |f| File.directory?(f) }.each do |lang|
      next if ['.git', '..', '.', 'ci', 'dist', 'docs', 'mappings'].include?(lang)

      exts = self.extensions_for_lang(lang)

      Dir.glob("#{lang}/**/rule-*.yml").each do |file|
        dirname = File.dirname(file)
        basename = File.basename(file, ".yml").delete_prefix("rule-")

        test_files = []

        exts.each do |ext|
          test_files.push("#{dirname}/test-#{basename}.#{ext}")
          test_files.push("#{dirname}/rule-#{basename}.#{ext}")
        end

        test_file_found = ""

        test_files.each do |test_file|
          if File.file?(test_file)
            # puts("test case file #{test_file} exists: ✔")
            test_file_found = test_file

            break
          end
        end

        if test_file_found.empty?
          puts("no test case file for #{file}: ✘")
          ok = false

          next
        end

        # TODO: When all test case files contain example code, this check should
        # be activated to check for empty test case files.
        # if self.empty_test_file?(test_file_found)
        #   puts("empty test case file #{test_file_found}: ✘")
        #   ok = false
        # end
      end
    end
    ok
  end

  def self.extensions_for_lang(lang)
    case lang
    when 'c'
      return ['c', 'cpp']
    when 'csharp'
      return ['cs']
    when 'javascript'
      return ['js', 'html']
    when 'python'
      return ['py']
    else
      return [lang]
    end
  end

  def self.empty_test_file?(filename)
    File.open(filename, "r") do |f|
      code_lines = f.readlines.delete_if { |l| l.strip!; l == "" || l.start_with?("//") }

      return code_lines.empty?
    end
  end
end

if TestCasePresenceCheck.run
  puts("test case files exist for all rules: ✔")
  exit(0)
else
  exit(-1)
end
